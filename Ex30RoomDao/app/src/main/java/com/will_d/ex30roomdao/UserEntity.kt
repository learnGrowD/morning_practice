package com.will_d.ex30roomdao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "gender")
    val gender : String?,

    @ColumnInfo(name = "birth")
    val birth : String?,

)
//이런식으로 primatyKey를 작성 할 수 있음...
//{
//  @PrimaryKey(autoGenerate = true) val id : Int = 0
//}
