package com.kaspresso.demo.screens

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.eakurnikov.kaspressosample.R

/**
 *Created by Madhuri on 2020-12-13
 */
object SecondScreen : Screen<SecondScreen>() {

    val title = KTextView { withId(R.id.tv_second_title) }
}