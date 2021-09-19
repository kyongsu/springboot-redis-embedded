### Docker + Redis 설정
##### 1. Redis 최신 이미지 가져오기
```bash
# docker pull redis
Unable to find image 'redis:latest' locally
latest: Pulling from library/redis
33847f680f63: Pull complete 
26a746039521: Pull complete 
18d87da94363: Pull complete 
5e118a708802: Pull complete 
ecf0dbe7c357: Pull complete 
46f280ba52da: Pull complete 
Digest: sha256:cd0c68c5479f2db4b9e2c5fbfdb7a8acb77625322dd5b474578515422d3ddb59
Status: Downloaded newer image for redis:latest
9118c48ec5c5b4dc4bc5df94bd71f2ee534004fdeccde0faf66ceffd4288d1d9
```

##### 2. 레디스 이미지 확인하기
```bash
> docker images
REPOSITORY   TAG                IMAGE ID       CREATED        SIZE
redis        latest             aa4d65e670d6   2 weeks ago    105MB
```

##### 3. 레디스 서버 실행하기
```bash
> docker run --name myredis -d -p 6379:6379 redis
```

##### 4. Docker의 redis-cli로 접속하기
```bash
> docker run -it --link myredis:redis --rm redis redis-cli -h redis -p 6379
redis:6379> set key value
OK
redis:6379> get key
"value"
redis:6379> exit
```

##### 5. Shell로 Docker 리눅스에 접속하기
```bash
> docker ps  
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS                                       NAMES
9118c48ec5c5   redis     "docker-entrypoint.s…"   12 minutes ago   Up 12 minutes   0.0.0.0:6379->6379/tcp, :::6379->6379/tcp   myredis

> docker exec -it myredis /bin/bash

root@9118c48ec5c5:/data# redis-cli -p 6379
127.0.0.1:6379> keys *
1) "key"
127.0.0.1:6379> get key
"value"
127.0.0.1:6379> exit
root@9118c48ec5c5:/data# 
```

##### 6. 기본명령어
```
$ select 1      # 1번 데이터 베이스 선택
$ select 0      # 0번 데이터 베이스 선택
$ keys *        # 모든 키 보여줘!
$ keys *index*  # index가 포함된 키 보여줘!
$ del abce      # abcd 키 지워줘!
```