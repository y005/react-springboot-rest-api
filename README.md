# [프로젝트] React - Spring Boot 상품 관리 API 구현 (클론 코딩)
## 프로젝트 설명 😎
React로 만들어진 Front End가 정해져있는 상황에서,
백엔드 개발자가 Spring Boot로 상품관리 API를 구현하여 A-Z 최종 서비스를 완성시켜봅니다.

## REST API 사용 명세서
- products API
    ```
    GET http://localhost:8080/api/v1/products
    ```
    
    ```
    [
      {
        "id": 74,
        "name": "kanu",
        "category": "coffee",
        "price": 1000,
        "description": null
      }
    ]
    ```

    ```
    POST http://localhost:8080/api/v1/products
    Content-Type: application/json
  
   {
      "name": "맥심",
      "category": "coffee",
      "price": 1000
   }
    ```

    ```
    successily create product
    ```
- orders API
    ```
    POST http://localhost:8080/api/v1/orders
    Content-Type: application/json

    {
      "email": "y005@naver.com",
      "address": "서울시",
      "postcode": "1234",
      "orderItems": [
       {
         "productId": 1234,
         "category": "coffee",
         "price": 1000,
         "quantity": 1
       },
       {
         "productId": 1235,
         "category": "coffee",
         "price": 1000,
         "quantity": 1
       }
       ]
    }
    ```
  
    ```
    successily create order
    ```

## 웹페이지 설명
- product db에 저장된 물품 정보가 반영된 구매 페이지
  ![](https://velog.velcdn.com/images/y005/post/b64a6215-127c-4d25-a404-a693034fbb4a/image.png)
  ![](https://velog.velcdn.com/images/y005/post/2fc87080-b17c-41d9-971e-70663f3d1857/image.png)
- 유효한 주문 이벤트 발생시 orders db와 orders_item db에 주문 내역과 주문한 품목 정보가 반영
  ![](https://velog.velcdn.com/images/y005/post/381ab7c3-a1d1-472e-b6dd-374202bb0919/image.png)
  ![](https://velog.velcdn.com/images/y005/post/870cd553-acdc-48f4-b8a2-0c227f012699/image.png)
  ![](https://velog.velcdn.com/images/y005/post/83463e3a-f6e6-4e74-b8b5-fb9d558e7561/image.png)