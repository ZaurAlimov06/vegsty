package com.app.vegsty.data.repository

import com.app.vegsty.data.remote.dto.Recipe
import com.app.vegsty.data.remote.service.MainService
import com.app.vegsty.ui.repository.MainRepository

class MainRepositoryImpl(
  private val mainService: MainService,
): MainRepository {

  override suspend fun getAllRecipes(): List<Recipe> {
    return listOf(
      Recipe(1,"https://images.immediate.co.uk/production/volatile/sites/30/2020/02/pumpkin-3f3d894.jpg?quality=90&resize=556,505", "detail1", "title1", 14, 15, 16, 17),
      Recipe(2,"https://green-connect.com.au/wp-content/uploads/2022/05/Cabbage2-scaled.jpg", "detail2", "title2", 14, 15, 16, 17),
      Recipe(3,"https://www.rhs.org.uk/getmedia/d063853a-10b2-4288-831c-b4c20e5b39e3/Potato-Charlotte-TS010230-600x400.jpg?width=600&height=400&ext=.jpg", "detail3", "title3", 14, 15, 16, 17)
    )
  }

  override suspend fun searchRecipes(searchText: String): List<Recipe> {
    return listOf(
      Recipe(1,"https://images.immediate.co.uk/production/volatile/sites/30/2020/02/pumpkin-3f3d894.jpg?quality=90&resize=556,505", "detail1", "title1", 14, 15, 16, 17),
      Recipe(3,"https://www.rhs.org.uk/getmedia/d063853a-10b2-4288-831c-b4c20e5b39e3/Potato-Charlotte-TS010230-600x400.jpg?width=600&height=400&ext=.jpg", "detail3", "title3", 14, 15, 16, 17)
    )
  }
}