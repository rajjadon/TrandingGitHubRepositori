package com.raj.jadon.data.trending.repo.tendingRepo.mapper

import com.raj.jadon.data.DataMapper
import com.raj.jadon.domain.model.TrendingRepoDao
import com.raj.jadon.network.model.TrendingRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrendingMapper @Inject constructor() :
    DataMapper<List<TrendingRepo>, List<TrendingRepoDao>> {
    override suspend fun mapToDao(entity: List<TrendingRepo>): List<TrendingRepoDao> {
        val list = emptyList<TrendingRepoDao>().toMutableList()

        if (entity.isNotEmpty())

            CoroutineScope(Dispatchers.Default).launch {
                entity.map {
                    list.add(
                        TrendingRepoDao(
                            author = it.author,
                            avatar = it.avatar,
                            currentPeriodStars = it.currentPeriodStars,
                            description = it.description,
                            forks = it.forks,
                            language = it.language,
                            languageColor = it.languageColor,
                            name = it.name
                        )
                    )
                }
            }

        return list
    }

}