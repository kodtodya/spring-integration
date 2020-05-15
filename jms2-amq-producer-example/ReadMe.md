Steps to setup AMQ-7 in your environment for the failover test:


1. Create brokers named "my-broker"

```
$ ./artemis create my-broker
```

2. Start the brokers

```
$ ./artemis run
```

3. Start the application and copy the queue name and use it inside the `spring-integration-jms-gateway` application's `common.xml`

4. Valdiate details inside admin-console of artemis.