package com.zeek1910.examples.data.repositories

import com.zeek1910.examples.R
import com.zeek1910.examples.models.MeditationItem
import kotlinx.coroutines.delay

class FakeMeditationRepository: MeditationRepository {

    override suspend fun getData(): List<MeditationItem> {
        delay(1000)
        return listOf(
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            )
        )
    }
}