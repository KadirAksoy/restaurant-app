<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proje Adı</title>
</head>
<body>
    <h1>Proje Adı</h1>

    <p>Bu proje, aşağıdaki teknolojiler kullanılarak geliştirilmiş bir uygulamadır:</p>
    
    <ul>
        <li>Spring Boot</li>
        <li>PostgreSQL</li>
        <li>Swagger</li>
        <li>Spring Security</li>
        <li>JWT (JSON Web Token)</li>
        <li>Aspect</li>
    </ul>

    <h2>Başlangıç</h2>

    <p>Bu talimatlar, projeyi yerel makinenizde çalıştırmak ve geliştirmek için size yol gösterecektir.</p>

    <h3>Önkoşullar</h3>

    <p>Projenin çalıştırılması için aşağıdaki yazılımların yüklü olması gerekmektedir:</p>

    <ul>
        <li>Docker</li>
        <li>Maven</li>
        <li>Java</li>
    </ul>

    <h3>Kurulum</h3>

    <ol>
        <li>Bu projeyi klonlayın:</li>
        <code>git clone https://github.com/kullanici/proje.git</code>

        <li>PostgreSQL veritabanını docker-compose.yml dosyası ile başlatın:</li>
        <code>docker-compose up -d</code>

        <li>Projeyi derlemek ve çalıştırmak için aşağıdaki Maven komutlarını kullanın:</li>
        <code>cd proje</code>
        <code>mvn clean install</code>
        <code>mvn spring-boot:run</code>
    </ol>



</body>
</html>
