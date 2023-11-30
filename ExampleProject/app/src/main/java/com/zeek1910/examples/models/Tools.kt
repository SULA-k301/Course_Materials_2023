package com.zeek1910.examples.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zeek1910.examples.R

enum class Tools(@DrawableRes val iconId: Int, @StringRes val titleId: Int) {
    MOOD_JOURNAL(R.drawable.ic_mood_journal, R.string.tools_mood_journal_title),
    MOOD_BOOSTER(R.drawable.ic_mood_journal, R.string.tools_mood_booster_title),
    POSITIVE_NOTES(R.drawable.ic_mood_journal, R.string.tools_positive_notes_title),
    TRIGGER_PLAN(R.drawable.ic_mood_journal, R.string.tools_trigger_plan_title),
    GOAL_TRAINER(R.drawable.ic_mood_journal, R.string.tools_goal_trainer_title)
}