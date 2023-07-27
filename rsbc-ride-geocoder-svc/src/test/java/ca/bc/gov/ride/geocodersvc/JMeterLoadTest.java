package ca.bc.gov.ride.geocodersvc;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JMeterLoadTest {

    @LocalServerPort
    private int port;

    @Test
    public void setupJMeter() throws IOException {
        ClassPathResource resource = new ClassPathResource("jmeter.properties");
        JMeterUtils.loadJMeterProperties(resource.getFile().getAbsolutePath());

        LoopController loopController = new LoopController();
        loopController.setLoops(1);

        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setNumThreads(10);
        threadGroup.setRampUp(5); // Ramp-up time (in seconds)
        threadGroup.setSamplerController(loopController);

        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setDomain("127.0.0.1");
        httpSampler.setPort(port);
        httpSampler.setPath("/geocodersvc/ping");
        httpSampler.setMethod("GET");

        TestPlan testPlan = new TestPlan("Test Plan");
        testPlan.addThreadGroup(threadGroup);

        ListedHashTree testPlanTree = new ListedHashTree();
        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        threadGroupHashTree.add(httpSampler);

        // Configure the arguments if necessary
        HTTPArgument httpArgument = new HTTPArgument("param1", "value1");
        httpArgument.setAlwaysEncoded(false);
        httpSampler.getArguments().addArgument(httpArgument);

        StandardJMeterEngine jmeterEngine = new StandardJMeterEngine();
        jmeterEngine.configure(testPlanTree);

        // Create a Summariser to collect summary information of the results
        Summariser summariser = new Summariser();
        ResultCollector resultCollector = new ResultCollector(summariser);
        testPlanTree.add(testPlanTree.getArray()[0], resultCollector);

        jmeterEngine.run();
    }
}
