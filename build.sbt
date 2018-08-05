lazy val root = (project in file("."))
  .settings(
    name                 := "ogc-tools-gml-jts",
    organization         := "io.github.soc",
    managedScalaInstance := false,
    crossPaths           := false,
    version              := "1.0.90",
    homepage             := Some(url("https://github.com/soc/ogc-tools-gml-jts")),
    licenses             := Seq("BSD" -> url("https://directory.fsf.org/wiki/License:BSD-2-Clause")),
    fork                 := true,
    // The javaHome setting can be removed if building against the latest installed version of Java is acceptable.
    // Running the tests requires removing the setting.
    // It can also be changed to point to a different Java version.
    // javaHome             := Some(file("/home/soc/apps/zulu6.19.0.1-jdk6.0.103-linux_x64/")),
    libraryDependencies  += "org.jvnet.jaxb2_commons" % "jaxb2-basics-runtime" % "0.6.0",
    libraryDependencies  += "com.sun.xml.bind"        % "jaxb-impl"            % "2.1.13"    % Provided,
    libraryDependencies  += "org.jvnet.ogc"           % "gml-v_3_1_1-schema"   % "1.0.3",
    libraryDependencies  += "com.vividsolutions"      % "jts"                  % "1.11",
    libraryDependencies  += "com.google.guava"        % "guava"                % "25.1-jre",
    libraryDependencies  += "junit"                   % "junit"                % "4.12"      % Test,
    libraryDependencies  += "com.novocode"            % "junit-interface"      % "0.11"      % Test,
    testOptions in Test  := Seq(Tests.Argument(TestFrameworks.JUnit, "-a")),
    // publishTo            := sonatypePublishTo.value,
    packageOptions in (Compile, packageBin) += {
      import java.util.jar.{Attributes, Manifest}
      val manifest = new java.util.jar.Manifest
      manifest.getMainAttributes.put(new Attributes.Name("Automatic-Module-Name"), "ogc.tools.gml.jts")
      Package.JarManifest(manifest)
    },
    pomExtra             :=
      <scm>
        <url>git@github.com:soc/ogc-tools-gml-jts.git</url>
        <connection>scm:git:git@github.com:soc/ogc-tools-gml-jts.git</connection>
      </scm>
      <developers>
        <developer>
          <id>soc</id>
          <name>Simon Ochsenreither</name>
          <url>https://github.com/soc</url>
          <roles>
            <role>Developer</role>
          </roles>
        </developer>
      </developers>
  )
