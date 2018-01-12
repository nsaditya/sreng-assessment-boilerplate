# Sr Engineer Assessment

The goal of the assessment is to evaluate your critical thinking when in front of an inefficient API. 

This repository contains some boilerplate code that you can use to start with, feel free to rearrange the java packages to make them more organized 

Your solution should handle failures and timeouts and make sure no data gets lost.

## Prerequisites
git, gradle, Java IDE

## Scenario
You are an e-commerce merchant and your customers are getting angry because it takes too long for you to ship their orders; at the end of each day you receive a spreadsheet file by email containing all the orders made for the entire day. This process is highly inefficient and your customers keep giving you bad reviews because they don't get any confirmation after making their order.

## Solution
You want new orders to be automatically pulled in from your marketplace. The marketplace provides an API endpoint that you will use to periodically call to get those new orders. From there you will need to check if you have sufficient inventory available to fulfill them and then decide whether or not the order is fulfillable.  

Using Spring Boot and the IDE of your choice, create a project that will fetch and process new orders. 

1. Periodically call the marketplace to pull orders

> See http://assessment.skubana.com/swagger-ui.html#!/Orders/getOrdersUsingGET
>
> Once orders are pulled, they won't be available anymore from the API endpoint so every subsequent call will provide new orders



2. For every order, you need to make an HTTP request to your fictional third party logistics partner (3PL) API to allocate inventory. This API is known to be slow when under heavy load, your solution should take that into account (you might want to consider pulling the orders first and then process them asynchronously)

> See http://assessment.skubana.com/swagger-ui.html#!/Product32Stocks/postProductStocksUsingPOST
>
> this API endpoint can return 2 different HTTP statuses

| HTTP Status | Details |
|:---:|:---|
| 200 | If inventory allocated successfully - Order is fulfillable |
| 400 | If inventory wasn't available for some reason - Order not fulfillable |




3. Implement the following actions depending of the order status. 

> Store into the datastore of your choice (it can be in memory)
>
> If the order is fulfillable, ship the order
> See http://assessment.skubana.com/swagger-ui.html#!/Shipments/postShipmentsUsingPOST

Once you've reached the 3 hours mark, please send over your project along with instructions to run it. If you have any ideas about ways to improve your project in order to make it closer to a production application, please provide that as well as this will be considered while reviewing your solution. 