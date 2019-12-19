package com.eakurnikov.kaspressosample.device

import android.Manifest
import android.os.Environment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/**
 * Pushes and then removes a file file placed at /artifacts directory.
 * [Files.push] uses the [FILE_PATH] relative path to push the file.
 * So, you should run the server with command `cd /absolute/path/to/project/directory & java -jar artifacts/desktop.jar`
 */
@RunWith(AndroidJUnit4::class)
class FilesTest : TestCase() {

    companion object {
        private const val FILE_NAME = "hello_world.apk"
        private const val FILE_RELATIVE_PATH = "artifacts/$FILE_NAME"
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun filesTest() {
        before {
            activityTestRule.launchActivity(null)
            /**
             * Some action to prepare the state
             */
        }.after {
            /**
             * Some action to revert the state
             */
        }.run {

            step("Push $FILE_RELATIVE_PATH to device") {
                device.files.push(
                    FILE_RELATIVE_PATH,
                    Environment.getExternalStorageDirectory().absolutePath
                )
                val file = File(Environment.getExternalStorageDirectory(), FILE_NAME)
                Assert.assertTrue(file.exists())
            }

            step("Delete pushed file") {
                val file = File(Environment.getExternalStorageDirectory(), FILE_NAME)
                device.files.remove(file.absolutePath)
                Assert.assertFalse(file.exists())
            }
        }
    }
}