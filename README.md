# kotlin-convenience-store-precourse

## 편의점

## 기능 목록
입력
- [ ] 구매할 상품과 수량을 입력 받는다.
- [ ] 프로모션 상품을 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력 받는다.
- [ ] 프로모션 재고가 부족하여 혜택 없이 결제해야 하는 경우, 일부 수량을 정가로 결제할 지 여부를 입력 받는다.
- [ ] 멤버십 할인 적용 여부를 입력 받는다.
- [ ] 추가 구매 여부를 입력 받는다.
출력
- [ ] 환영 인사를 출력한다.
- [ ] 보유 상품 안내 메시지를 출력한다.
- [ ] 현재 재고를 출력한다. (재고가 0개라면 재고 없음)을 출력한다.
- [ ] 프로모션 상품을 수량보다 적게 가져온 경우, 혜택 안내 메시지를 출력한다.
- [ ] 프로모션 재고가 부족하여 혜택 없이 결제해야 하는 경우, 정가 결졔 여부 메시지를 출력한다.
- [ ] 멤버십 할인 적용 여부 메시지를 출력한다.
- [ ] 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다.
- [ ] 추가 구매 여부 메시지를 출력한다.
---

## 세부 기능 목록
### model

### `Promotion`
프로모션 종류와 그 정보를 가지고 있는 클래스
```kotlin
enum class Promotion(
    val promotionName: String,
    val buy: Int,
    val get: Int,
    var promotionCount: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
)
```
- 탄산2+1
- MD추천상품
- 반짝할인
- NULL

`isAddable()` : 프로모션 혜택을 받을 수 있는 지 확인
`getPresentedQuantity()` : 증정 상품 수량 반환 

### `PurchaseProduct`
사용자가 구매한 상품 정보를 가지고 있는 클래스
```kotlin
data class PurchaseProduct(
    val name: String,
    var price: Int,
    var quantity: Int,
    val promotion: Promotion,
    var totalPrice: Int = 0,
    var isPromotion: Boolean = false,
    var presentedQuantity: Int = 0,
    var discountPrice: Int = 0
)
```
`printQuantityAndPrice()` : 상품 수량과 가격 출력
`printPresentedQuantity()` : 증정 상품 수량 출력

### `Recipt`
구매한 상품에 대해서 총 금액, 수량, 할인 금액 등을 가지고 있는 클래스
```kotlin
data class Receipt(
    var totalPrice: Int = 0,
    var totalQuantity: Int = 0,
    var promotionDiscount: Int = 0,
    var membershipDiscount: Int = 0,
    var payMoney: Int = 0
)
```

### `Stock`
products.md 에 있는 상품 정보를 저장하여 가지고 있는 클래스
```kotlin
data class Stock(
    val name: String,
    val price: Int,
    var quantity: Int = 0,
    val promotion: Promotion,
    var promotionQuantity: Int = 0,
    var isPromotion: Boolean = false
) 
```
`printStock()` : 재고 출력
### `UserAnswer`
사용자의 답변(Y/N) 을 가지고 있는 클래스
```kotlin
enum class UserAnswer {
    YES, NO
}
```
`when` 으로 처리하기 위해 `enum` 으로 구현

### view
### `InputView`
- 어떤 입력이든 받기만 한다
- 입력 값을 관리하는 로직은 서비스 레이어에서 처리
### `OutputView`
- [x] 환영 인사와 보유 상품 안내 메시지 출력
- [x] 현재 재고 출력 (재고가 없으면 "재고 없음")
- [x] 구매 안내 메시지 출력
- [x] 프로모션 상품 혜택 안내 메시지 출력
- [x] 프로모션 재고 부족 시 정가 결제 여부 메시지 출력
- [x] 멤버십 할인 적용 여부 메시지 출력
- [x] 구매 상품 내역, 증정 상품 내역, 금액 정보 출력
- [x] 추가 구매 여부 메시지 출력

### service
### `IOService`
입출력을 관리하고, 입력의 유효성을 검증하는 클래스
- [x] 구매 상품과 수량을 입력 받음
- [x] 프로모션 상품을 수량보다 적게 가져온 경우, 추가 여부를 입력 받음
- [x] 프로모션 재고가 부족하여 혜택 없이 결제해야 하는 경우, 일부 수량을 정가로 결제할 지 여부를 입력 받음
- [x] 멤버십 할인 적용 여부를 입력 받음
- [x] 추가 구매 여부를 입력 받음

### `PromotionService`
프로모션 정보를 관리하는 클래스
- [x] 프로모션 상품인 경우, 증정 수량을 기록함
- [x] 프로모션 상품인 경우, 추가 혜택 여부와 정가 결제 여부를 물어봄

### `ReceiptService`
금액 결제 관련 영수증을 기록하는 클래스
- [x] 총구매액 관리
- [x] 총 수량 관리
- [x] 프로모션 할인 관리
- [x] 멤버십 할인 관리
- [x] 총 결제 금액 관리

### `StockService`
재고 정보를 관리하는 클래스
- [x] 파일로부터 재고 정보를 읽어서 엔티티에 저장
- [x] 재고 정보를 반환
- [x] 재고 정보를 업데이트

### controller
### `ConvinienceStoreController`
1. 구매 수량과 품목을 입력받음
2. 프로모션 상품인지 확인 
   1. 프로모션 상품이고, 프로모션 재고가 충분한 지 확인
      1. 프로모션 재고가 충분하면, 추가 혜택 여부를 물어봄
      2. 프로모션 재고가 부족하면, 정가 결제 여부를 물어봄
   2. 일반 상품이면, 그냥 계산함

3. 멤버십 여부를 물어봄
4. 총 가격과 수량을 계산
5. 영수증 출력
6. 재고 상황 업데이트
7. 추가 구매 여부를 물어봄.
   1. 추가 구매 시 1번부터 다시 시작
   2. 아니면 프로그램 종료


### `StockController`
1. 파일로부터 재고 정보를 읽어서 엔티티에 저장
2. 재고 정보를 반환
3. 재고 정보를 업데이트

### util
### `Validator`
- [ ] 구매 상품, 수량 입력의 형식 검증
    1. 빈 문자열 x, 공백 문자열 x
    2. 대괄호로 묶여 있어야 함
    3. 쉼표로 구분되어 있어야 함 
    4. 첫 입력 `[` 
    5. 마지막 입력 `]`
- [ ] 구매 상품 검증
- [ ] 구매 수량 검증
- [ ] Y / N 입력 검증

### `ErrorMessage`
### `InputMessage`
### `OutputMessage`

### `Converter`

## 유효성 검증
### 구입 상품과 수량 입력 자체의 검증
1. 개별 상품은 대괄호([]) 로 묶여야 함
2. 개별 상품은 쉼표(,) 로 구분되어야 함

### 구입 상품 검증
1. products.md 에 있는 상품명과 100% 동일해야 함

### 구매 수량 검증
1. 1 이상의 정수이어야 함
2. 재고보다 적거나 같은 수량이어야 함

### Y / N 검증
1. Y 인지 검증
2. N 인지 검증

## 프로그래밍 요구 사항

- indent depth를 3이 넘지 않도록 구현한다.
- 단일 책임 원칙을 최대한 준수한다.
- 함수 길이가 10라인을 넘지 않도록 구현한다.
- else 를 지양한다.
- Enum 클래스를 적용하여 구현한다.
- 기능 목록을 작성하고 정상적으로 작동하는 지 테스트 코드로 확인한다.
- 기능이 전체적으로 작동하는 지 단위 테스트로 확인한다.
-

## 개인적 목표(신규)
- [ ] 테스트 케이스를 위해 기존 코드를 변경하지 않기
- [ ] 테스트 케이스를 고려해서 함수를 작성하기
  - 함수의 내부 로직, 파라미터, 반환 값 등을 고려해야 함


## 개인적 목표(기존)
- [ ] 테스트 케이스에 assertj, junit5 잘 사용하기
- [ ] 예외 사항을 미리 몇 가지 작성하고 시작하기
- [ ] 기능 하나하나 테스트 케이스 작성하기 (자명한 것 제외)
- [ ] 요구 사항을 더 자세하기 기술하기
- [ ] 기능들을 완전 자세하기 나누기 (함수)
- [ ] 코틀린 문법들을 사용해 코드를 간결하게 짜기
- [ ] MVC 패턴 적용하기
- [ ] 입,출력메시지를 상수, 변수화 하기
- [ ] 예외 케이스 다양하게 생각하기
- [ ] 테스트 코드 다양하게 작성하기
