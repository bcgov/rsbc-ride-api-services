import json

def validatepayload(payload):
    if not('tablename' in payload):
        return False
    if not('schema' in payload):
        return False
    if not('destination' in payload):
        return False
    if not('data' in payload):
        return False
    if not('source' in payload):
        return False
    return True


def bi_prepquerystring(payload):
    tmpstr=''
    # tmpPayload=json.loads(payload)
    for k,v in payload.items():
        if not(v==None):
            tmpstr=tmpstr+f"{k}='{v}'"+ " AND "
    return tmpstr[:-5]

    
    