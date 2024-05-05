# Proje Adı

Bu proje, aşağıdaki teknolojiler kullanılarak geliştirilmiş bir uygulamadır:

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
git clone https://github.com/kullanici/proje.git
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

