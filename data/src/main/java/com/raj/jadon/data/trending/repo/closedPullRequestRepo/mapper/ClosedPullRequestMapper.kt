package com.raj.jadon.data.trending.repo.closedPullRequestRepo.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.raj.jadon.data.DataMapper
import com.raj.jadon.domain.model.ClosedPullRequestUser
import com.raj.jadon.domain.model.User
import com.raj.jadon.network.model.PullRequestUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ClosedPullRequestMapper @Inject constructor() :
    DataMapper<List<PullRequestUser>, List<ClosedPullRequestUser>> {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun mapToDao(entity: List<PullRequestUser>): List<ClosedPullRequestUser> {
        val list = emptyList<ClosedPullRequestUser>().toMutableList()

        if (entity.isNotEmpty())

            CoroutineScope(Dispatchers.Default).launch {
                entity.map {
                    list.add(
                        ClosedPullRequestUser(
                            title = it.title ?: "",
                            created_date = it.created_date.parseDate() ?: "",
                            closed_date = it.closed_date.parseDate() ?: "",
                            user = User(
                                name = it.user.name,
                                avatar_url = it.user.avatar_url ?: ""
                            )
                        )
                    )
                }
            }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun String?.parseDate() = kotlin.run {
        this?.let {
            val dateTime: ZonedDateTime =
                OffsetDateTime.parse(this).toZonedDateTime()  // parsed date
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm a")
            dateTime.format(formatter)
        } ?: kotlin.run { null }
    }
}