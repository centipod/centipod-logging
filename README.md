# centipod-logging

Convenience wrapper for Logback logging

# Repositories

```
<repositories>
    <repository>
        <id>gitlab-maven</id>
        <url>https://dev.centipod.io/api/v4/projects/29/packages/maven</url>
    </repository>
</repositories>

<distributionManagement>
    <repository>
        <id>gitlab-maven</id>
        <url>https://dev.centipod.io/api/v4/projects/29/packages/maven</url>
    </repository>
    <snapshotRepository>
        <id>gitlab-maven</id>
        <url>https://dev.centipod.io/api/v4/projects/29/packages/maven</url>
    </snapshotRepository>
</distributionManagement>

```

```
<dependencies>
    <dependency>
        <groupId>io.centipod</groupId>
        <artifactId>centipod-logging</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
<
```