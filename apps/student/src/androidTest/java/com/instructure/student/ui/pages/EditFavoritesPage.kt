/*
 * Copyright (C) 2019 - present Instructure, Inc.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, version 3 of the License.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.instructure.student.ui.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.instructure.canvasapi2.models.Course
import com.instructure.espresso.assertDisplayed
import com.instructure.espresso.page.BasePage
import com.instructure.espresso.page.withAncestor
import com.instructure.student.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString

class EditFavoritesPage : BasePage(R.id.editFavoritesPage) {

    fun assertCourseDisplayed(course: Course) {
        val itemMatcher = allOf(withText(containsString(course.originalName)), withId(R.id.title))
        onView(allOf(withId(R.id.listView), withAncestor(R.id.editFavoritesPage)))
                .perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(itemMatcher)))
        onView(itemMatcher).assertDisplayed()
    }

    fun assertCourseFavorited(course: Course) {
        val itemMatcher = allOf(
                withContentDescription(containsString(", favorite")),
                withText(containsString(course.originalName)),
                withId(R.id.title))
        onView(allOf(withId(R.id.listView), withAncestor(R.id.editFavoritesPage)))
                .perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(itemMatcher)))
        onView(itemMatcher).assertDisplayed()
    }

    fun assertCourseNotFavorited(course: Course) {
        val itemMatcher = allOf(
                withContentDescription(containsString(", not favorite")),
                withText(containsString(course.originalName)),
                withId(R.id.title))
        onView(allOf(withId(R.id.listView), withAncestor(R.id.editFavoritesPage)))
                .perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(itemMatcher)))
        onView(itemMatcher).assertDisplayed()
    }

    fun toggleCourse(course: Course) {
        val itemMatcher = allOf(withId(R.id.star), hasSibling(allOf(withText(containsString(course.originalName)), withId(R.id.title))))
        onView(allOf(withId(R.id.listView), withAncestor(R.id.editFavoritesPage)))
                .perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(itemMatcher)))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(hasDescendant(itemMatcher), click()))
    }


}

