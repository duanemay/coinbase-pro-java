# Coinbase Pro :: Assertions

A package for testing the coinbasepro models with assertJ

# Usage

Set the Version
```xml
  <properties>
    <coinbasepro-assertions.version>1.0.0-SNAPSHOT</coinbasepro-assertions.version>
  </properties>
```

Declare the dependency
```xml
  <dependency>
    <groupId>com.mayhoo</groupId>
    <artifactId>coinbasepro-assertions</artifactId>
    <version>${coinbasepro-assertions.version}</version>
    <scope>test</scope>
  </dependency>
```

Import the assertThat methods
```java
import static com.mayhoo.coinbasepro.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
```
