package com.raj.jadon.data.trending.repo.closedPullRequestRepo.mapper

import com.raj.jadon.data.DataMapper
import com.raj.jadon.domain.model.ClosedPullRequestUser
import com.raj.jadon.domain.model.User
import com.raj.jadon.network.model.PullRequestUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClosedPullRequestMapper @Inject constructor() :
    DataMapper<List<PullRequestUser>, List<ClosedPullRequestUser>> {
    override suspend fun mapToDao(entity: List<PullRequestUser>): List<ClosedPullRequestUser> {
        val list = emptyList<ClosedPullRequestUser>().toMutableList()

        if (entity.isNotEmpty())

            CoroutineScope(Dispatchers.Default).launch {
                entity.map {
                    list.add(
                        ClosedPullRequestUser(
                            title = it.title ?: "",
                            created_date = it.created_date ?: "",
                            closed_date = it.closed_date ?: "",
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
}