# shipment-resolution-assistant
A sample Shipment Resolution application demonstrating the usage of Generative AI in Java based applications.

It consist of 2 modules

* shipment-resolution-service
* shipment-details-service

# shipment-resolution-service

The Shipment Resolution Service is an AI-driven orchestration layer that autonomously analyzes shipment events, retrieves contextual data, and notifies customers using AWS Bedrock-powered reasoning. This exposes a REST endpoint whihc can be called from messsage listener or any other API to trigger the Agentic flow.

### How to run?
**This will require a docker runtime on your machine.**
1. go to project root directory on the command line and run the following command
    ```
    docker build -t shipment-resolution-service .
    ```
2. Run the folowing command. 
    ```
    docker run -p 8080:8080 shipment-resolution-service
    ```

# shipment-details-service

A spring boot REST api, which exposes a single endpoint. This api will act as a external api which will provides dummy shipment information. 

### How to run?
**This will require a docker runtime on your machine.**
1. go to project root directory on the command line and run the following command
    ```
    docker build -t shipment-details-service .
    ```
2. Run the folowing command. 
    ```
    docker run -p 8089:8089 shipment-details-service
    ```
