## SSL Setup
Refer the link [common-java-keytool-commands](https://www.sslshopper.com/article-most-common-java-keytool-keystore-commands.html) to learn more about keytool.

### Generate self-signed SSL certificate and java keystore

```bash
keytool -genkeypair -alias identity -keyalg RSA -keysize 2048 -keystore identity.jks -validity 3650
```

### Convert java keystore to .p12 format

```bash
keytool -importkeystore -srckeystore identity.jks -destkeystore identity.p12 -deststoretype pkcs12
```

### Export public certificate from the keystore

```bash
keytool -export -keystore identity.p12 -alias identity -file recipemanagerapi.crt
```


