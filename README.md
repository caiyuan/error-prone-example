# Error Prone

Error Prone is a static analysis tool for Java that catches common programming mistakes at compile-time.

```java
public class ShortSet {

    public static void main(String[] args) {
        Set<Short> s = new HashSet<>();
        int max = 100;
        for (short i = 0; i < max; i++) {
            s.add(i);
            s.remove(i - 1);
        }
        System.out.println(s.size());
    }

}
```

```
[ERROR] /Users/Ryan/Documents/workspace/error-prone-example/src/main/java/me/caiyuan/example/ShortSet.java:[16,21]
        [CollectionIncompatibleType] Argument 'i - 1' should not be passed to this method; its type int is not compatible with its collection's type argument Short
    (see https://errorprone.info/bugpattern/CollectionIncompatibleType)
  Did you mean '@SuppressWarnings("CollectionIncompatibleType") public static void main(String[] args) {'?
[INFO] 1 error
```

## Getting Started

Our documentation is at [errorprone.info](https://errorprone.info).

## Developing Error Prone

Developing and building Error Prone is documented on the [wiki](https://github.com/google/error-prone/wiki/For-Developers).

## Installing

### IntelliJ IDEA

To add the plugin, start the IDE and find the Plugins dialog. Browse Repositories, choose Category: Build, and find the [Error Prone](https://plugins.jetbrains.com/plugin/7349-error-prone-compiler/) plugin. Right-click and choose “Download and install”. The IDE will restart after you’ve exited these dialogs.

Allows to build projects using Error Prone Java compiler to catch common Java mistakes at compile-time. To use the compiler, go to File | Settings | Compiler | 'Java Compiler' and select 'Javac with error-prone' in 'Use compiler' box.

### Maven

#### 使用 JDK 9+

```shell script
$ set JAVA_HOME=/Users/Ryan/.sdkman/candidates/java/11.0.5.hs-adpt

$ mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3/libexec
Java version: 11.0.5, vendor: AdoptOpenJDK, runtime: /Users/Ryan/.sdkman/candidates/java/11.0.5.hs-adpt
Default locale: zh_CN_#Hans, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.6", arch: "x86_64", family: "mac"
```

```xml
<project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                    <compilerArgs>
                        <arg>-XDcompilePolicy=simple</arg>
                        <arg>-Xplugin:ErrorProne</arg>
                    </compilerArgs>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>com.google.errorprone</groupId>
                            <artifactId>error_prone_core</artifactId>
                            <version>2.3.4</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

#### 使用 JDK 8

```shell script
$ set JAVA_HOME=/Users/Ryan/.sdkman/candidates/java/8.0.232.hs-adpt/

$ mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3/libexec
Java version: 1.8.0_232, vendor: AdoptOpenJDK, runtime: /Users/Ryan/.sdkman/candidates/java/8.0.232.hs-adpt/jre
Default locale: zh_CN, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.6", arch: "x86_64", family: "mac"
```

```xml
<project>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <javac.version>9+181-r4173-1</javac.version>
    </properties>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                    <compilerArgs>
                        <arg>-XDcompilePolicy=simple</arg>
                        <arg>-Xplugin:ErrorProne</arg>
                    </compilerArgs>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>com.google.errorprone</groupId>
                            <artifactId>error_prone_core</artifactId>
                            <version>2.3.4</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
    <!-- using github.com/google/error-prone-javac is required when running on JDK 8 -->
    <profiles>
        <profile>
            <id>jdk8</id>
            <activation>
                <jdk>1.8</jdk>
            </activation>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <configuration>
                            <fork>true</fork>
                            <compilerArgs combine.children="append">
                                <arg>
                                    -J-Xbootclasspath/p:${settings.localRepository}/com/google/errorprone/javac/${javac.version}/javac-${javac.version}.jar
                                </arg>
                            </compilerArgs>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
</project>
```
