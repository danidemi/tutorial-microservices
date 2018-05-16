Get the list of resources that should be managed as they
were transactional, through a Saga.

    > http http://localhost:9090/resources
    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Date: Fri, 04 May 2018 15:43:52 GMT
    Transfer-Encoding: chunked
    
    [
        "CREDIT_CARD",
        "SMS_NOTIFICATION",
        "INVENTORY"
    ]
    
Commit a resource for a given transaction.

    > http post http://localhost:9090/CREDIT_CARD/10
    
Rollback a resource for a given transaction.

    > http post http://localhost:9090/SMS_NOTIFICATION/25  
    
Get the status of the pending "transactions".  

    > http http://localhost:9090/status
    
Reset the status

    > http post http://localhost:9090/reset
