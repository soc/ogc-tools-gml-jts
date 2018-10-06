lazy val root = (project in file("."))
  .settings(
    name                 := "ogc-tools-gml-jts",
    organization         := "io.github.soc",
    managedScalaInstance := false,
    crossPaths           := false,
    version              := "1.1.90",
    homepage             := Some(url("https://github.com/soc/ogc-tools-gml-jts")),
    licenses             := Seq("BSD" -> url("https://directory.fsf.org/wiki/License:BSD-2-Clause")),
    fork                 := true,
    // The javaHome setting can be removed if building against the latest installed version of Java is acceptable.
    // Running the tests requires removing the setting.
    // It can also be changed to point to a different Java version.
    //javaHome             := Some(file("/home/soc/apps/zulu8.31.0.1-jdk8.0.181-linux_x64/")),
    libraryDependencies  += "org.jvnet.jaxb2_commons" % "jaxb2-basics-runtime" % "1.11.1",
    libraryDependencies  += "org.jvnet.ogc"           % "gml-v_3_1_1-schema"   % "1.1.0",
    libraryDependencies  += "org.locationtech.jts"    % "jts-core"             % "1.16.0",
    testOptions in Test  := Seq(Tests.Argument(TestFrameworks.JUnit, "-a")),
    //publishTo            := sonatypePublishTo.value,
    packageOptions in (Compile, packageBin) += {
      import java.util.jar.{Attributes, Manifest}
      val manifest = new Manifest
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
