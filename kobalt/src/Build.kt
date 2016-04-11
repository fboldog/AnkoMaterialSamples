import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.android.*
import com.beust.kobalt.plugin.java.*

val pl = plugins("com.beust:kobalt-android:0.83")

val kotlinVersion = "1.0.2-eap-15"
val ankoVersion = "0.8.3"
val supportVersion = "23.3.0"

val p = project {

    name = "AnkoMaterialSamples"
    group = "com.ferencboldog"
    artifactId = name
    version = "0.1"

    android {
        compileSdkVersion = "23"
        buildToolsVersion = "23.0.3"
        applicationId = "com.ferencboldog.ankomaterial"
    }

    dependencies {
        val support = listOf("appcompat-v7", "design", "support-v4", "recyclerview-v7")
        support.forEach { name ->
            compile("com.android.support:$name:$supportVersion")
        }

        val anko = listOf("sdk15", "support-v4", "design", "appcompat-v7", "recyclerview-v7")
        anko.forEach { name ->
            compile("org.jetbrains.anko:anko-$name:$ankoVersion")
        }

    }

//    dependenciesTest {
//        compile("junit:junit:4.12")
//        compile("org.assertj:assertj-core:2.4.0")
//        compile("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
//        compile("org.robolectric:robolectric:3.1-SNAPSHOT")
//    }
}
