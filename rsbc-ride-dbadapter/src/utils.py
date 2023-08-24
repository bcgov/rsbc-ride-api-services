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


def bi_prepquerystring(payload,primarykeys=None):
    tmpstr=''
    # tmpPayload=json.loads(payload)
    if primarykeys==None:
        for k,v in payload.items():
            if not(v==None):
                tmpstr=tmpstr+f"{k}='{v}'"+ " AND "
    else:
        for k in primarykeys:
            if payload[k] != None:
                tmpstr=tmpstr+f"{k}='{payload[k]}'"+ " AND "
    return tmpstr[:-5]

def bi_prepinsertstring(payload,tablename,schema):
    columns = ', '.join(payload.keys())
    values = ', '.join([f"'{value}'" for value in payload.values()])
    sql_query = f"INSERT INTO {schema}.{tablename} ({columns}) VALUES ({values})"

    return sql_query

def bi_prepupsertstring(payload,tablename,schema,primarykeys):
    set_clause = ', '.join([f"{key} = '{value}'" for key, value in payload.items()])
    where_clause = ' AND '.join([f"{cols} = '{payload[cols]}'" for cols in primarykeys])
    upsertstr = f"UPDATE {schema}.{tablename} SET {set_clause} WHERE {where_clause}"
    return upsertstr








    
    