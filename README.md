# spring-boot-cluster-examples
spring-bootのセッションクラスタリングサンプル

## DynamoDBの場合
### 制限事項
* sticky session前提  
  DynamoDBへの書き込み頻度は最高でも60秒ごと、リアルタイムにはならない

### 起動方法
以下のプロパティを起動時に指定
* session.store.type=dynamo-db
* aws.access.key=[AWSのアクセスキーID]
* aws.secret.key=[AWSのシークレットキー]
* aws.dynamodb.region.id=[DynamoDBがあるリージョンのID]

## Redisの場合
Spring Sessionを利用する
* 未実装
