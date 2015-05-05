# spring-boot-cluster-examples
spring-bootのセッションクラスタリングサンプル

## DynamoDBの場合
#### 制限事項

* sticky session前提  
  DynamoDBへの書き込み頻度は最高でも60秒ごと、リアルタイムにはならない
* apacheのmod_proxy_balancerを使う場合、jvmRouteの指定ができないためmod_headersを使ったsticky session方式を取る必要がある。

#### 起動方法
以下のプロパティを起動時に指定

* session.store.type=dynamo-db
* aws.access.key=[AWSのアクセスキーID]
* aws.secret.key=[AWSのシークレットキー]
* aws.dynamodb.region.id=[DynamoDBがあるリージョンのID]

#### 設定上の注意点
* DynamoDBSessionManagerの最新版(Tomcat8対応版）はMaven Repositoryに存在しないのでjarファイルをダウンロードする必要がある

## Redisの場合
Spring Sessionを利用する  
枯れているライブラリはhttps://github.com/jcoleman/tomcat-redis-session-manager だが、
Tomcat8には対応していない。
Redisが完全にSessionデータの置き場所となるのでsticky sessionにする意味はない。

#### 起動方法
以下のプロパティを起動時に指定

* session.store.type=redis
* spring.redis.host=[redis host]
* spring.redis.password=[redis password] //設定していなければ不要
* spring.redis.port=[redis port]

#### 検証環境の起動
* DockerでのSpring Session Clusterの起動方法
* DockerとDocker Composeがインストール済みであることが前提

docker-compose.ymlのVIRTUAL_HOST二箇所を、dockerのホストへ変更した後以下のコマンドを実行

```
./startup-spring-session-cluster.sh
```

以下にアクセスするとロードバランサ（NGINX)経由でアクセスできる。

```
http:/dockerのホスト/
```

