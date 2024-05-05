# Proje Adı

Kullanılan teknolojiler.

- Spring Boot
- PostgreSQL
- Swagger
- Spring Security
- JWT (JSON Web Token)
- Aspect

## Başlangıç

Bu talimatlar, projeyi yerel makinenizde çalıştırmak ve geliştirmek için size yol gösterecektir. 

### Önkoşullar

Projenin çalıştırılması için aşağıdaki yazılımların yüklü olması gerekmektedir:

- Docker
- Maven
- Java

### Kurulum

1. Bu projeyi klonlayın:

```
https://github.com/KadirAksoy/restaurant-app.git
```



2. PostgreSQL veritabanını docker-compose.yml dosyası ile başlatın:

```
docker-compose up -d
```

3. Projeyi derlemek ve çalıştırmak için aşağıdaki Maven komutlarını kullanın:

```
cd proje
mvn clean install
mvn spring-boot:run
```

### Swagger
Gerekli adımları tamamladıktan sonra uygulamayı çalıştırıp aşağıdaki adrese giderek Swagger arayüzüne erişebilirsiniz:

http://localhost:8080/swagger-ui/index.html#/
