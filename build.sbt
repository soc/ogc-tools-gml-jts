lazy val root = (project in file("."))
  .settings(
    name                 := "ogc-tools-gml-jts",
    organization         := "org.ogc-schemas",
    managedScalaInstance := false,
    crossPaths           := false,
    version              := "3.0.1",
    homepage             := Some(url("https://github.com/ogc-schemas/ogc-tools-gml-jts")),
    licenses             := Seq("BSD" -> url("https://directory.fsf.org/wiki/License:BSD-2-Clause")),
    fork                 := true,
    // The javaHome setting can be removed if building against the latest installed version of Java is acceptable.
    // Running the tests requires removing the setting.
    // It can also be changed to point to a different Java version.
    //javaHome             := Some(file("/home/soc/apps/zulu8.33.0.1-jdk8.0.192-linux_x64/")),
    libraryDependencies  += "jakarta.xml.bind"        % "jakarta.xml.bind-api" % "2.3.3",
    libraryDependencies  += "org.ogc-schemas"         % "gml-v_3_1_1"          % "3.0.0",
    libraryDependencies  += "org.locationtech.jts"    % "jts-core"             % "1.18.0",
    testOptions in Test  := Seq(Tests.Argument(TestFrameworks.JUnit, "-a")),
    /*
    publishTo            := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    */
    packageOptions in (Compile, packageBin) += {
      import java.util.jar.{Attributes, Manifest}
      val manifest = new Manifest
      manifest.getMainAttributes.put(new Attributes.Name("Automatic-Module-Name"), "ogc.tools.gml.jts")
      Package.JarManifest(manifest)
    },
    pomExtra             :=
      <scm>
        <url>git@github.com:ogc-schemas/ogc-tools-gml-jts.git</url>
        <connection>scm:git:git@github.com:ogc-schemas/ogc-tools-gml-jts.git</connection>
      </scm>
      <developers>
        <developer>
          <id>soc</id>
          <name>Simon Ochsenreither</name>
          <url>https://github.com/soc</url>
          <roles>
            <role>current maintainer</role>
          </roles>
        </developer>
      </developers>
  )
