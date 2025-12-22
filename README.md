# Install and Run Apache Karaf
---
1. Install Apache Karaf from [Apache Karaf - Download](https://karaf.apache.org/download.html) or specifically [Apache Download Mirrors](https://www.apache.org/dyn/closer.lua/karaf/4.4.9/apache-karaf-4.4.9.zip).
2. Unzip the file to `/<USER>/.dependencies/`
3. Run `cd /path/to/karaf/bin` then run `.\karaf.bat`. You'll going to see the following:
4. Run the following commands:
```bash
# GPT if any commands fail.
feature:install scr rs cxf-jaxrs

feature:repo-add mvn:org.apache.cxf.karaf/apache-cxf/3.5.6/xml/features

bundle:install -s mvn:com.fasterxml.jackson.core/jackson-annotations/2.17.2
bundle:install -s mvn:com.fasterxml.jackson.core/jackson-core/2.17.2
bundle:install -s mvn:com.fasterxml.jackson.core/jackson-databind/2.17.2
bundle:install -s mvn:com.fasterxml.jackson.jaxrs/jackson-jaxrs-base/2.17.2

feature:repo-add mvn:org.apache.aries.jax.rs/org.apache.aries.jax.rs.features/2.0.2/xml
feature:install aries-jax-rs-whiteboard
feature:install aries-jax-rs-whiteboard-jackson
feature:install aries-jax-rs-whiteboard-rest-management
```

5. Run `bundle:list` to check whether the bundle is installed successfully
   ![[Apache Karaf Setup-1766362424646.png]]

6. Run `mvn clean package` each time you have written new code. Then copy & paste the `.jar` file to `/path/to/karaf/deploy/`.  The application should start normally.

# Basic Apache Karaf Commands
---
```
.\karaf.bat
.\karaf.bat debug

# Check currently available bundles
bundle:list

# Check DS components
scr:list

# Quit Apache Karaf
shutdown

# Others
log:tail
feature:list | grep -i scr
bundle:stop
bundle:uninstall
rm deploy/<bundle-name>.jar
```