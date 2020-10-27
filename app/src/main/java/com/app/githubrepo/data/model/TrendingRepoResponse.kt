package com.app.githubrepo.data.model

data class TrendingRepoResponse(
    val author: String,
    val name: String,
    val avatar: String,
    val url: String,
    val description: String,
    val language: String,
    val languageColor: String,
    val stars: String,
    val forks: String,
    val builtBy: BuiltBy
) {

    data class BuiltBy(
        val href: String,
        val avatar: String,
        val username: String
    )

    /*  {
          "author": "xingshaocheng",
          "name": "architect-awesome",
          "avatar": "https://github.com/xingshaocheng.png",
          "url": "https://github.com/xingshaocheng/architect-awesome",
          "description": "后端架构师技术图谱",
          "language": "Go",
          "languageColor": "#3572A5",
          "stars": 7333,
          "forks": 1546,
          "currentPeriodStars": 1528,
          "builtBy": [
          {
              "href": "https://github.com/viatsko",
              "avatar": "https://avatars0.githubusercontent.com/u/376065",
              "username": "viatsko"
          }
          ]
      }*/
}