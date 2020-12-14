package com.kaspresso.demo.tests

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eakurnikov.kaspressosample.R
import com.kaspresso.demo.utils.matchers.ViewSizeMatcher.Companion.withWidthAndHeight
import com.kaspresso.demo.utils.scenario.TypeTextAndCheckTitleScenario
import com.kaspresso.demo.screens.MainScreen
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Madhuri on 2020-12-13
 */
@RunWith(AndroidJUnit4::class)
class PerfectKaspressoTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun perfectKaspressoTest() {
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
            step("Open Simple screen") {
                MainScreen {
                    title.hasText(R.string.main_title)
                    title.hasTextColor(R.color.colorPrimary)

                    toSimpleScreenBtn {
                        isVisible()
                        isClickable()
                        matches {
                            withText(R.string.simple_screen)
                            withMatcher(Matchers.not(withWidthAndHeight(42f, 42f)))
                        }
                        click()
                    }
                }
            }

            scenario(TypeTextAndCheckTitleScenario("1"))
            scenario(TypeTextAndCheckTitleScenario(" "))
            scenario(TypeTextAndCheckTitleScenario("Kaspresso"))
            scenario(TypeTextAndCheckTitleScenario(""))
        }
    }

}