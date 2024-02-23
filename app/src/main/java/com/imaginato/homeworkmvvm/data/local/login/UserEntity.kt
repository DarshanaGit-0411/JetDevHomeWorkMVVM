package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey val userId: String,
    @ColumnInfo(name = "UserName") var name: String? = null,
    @ColumnInfo(name = "xAcc") var xAcc: String? = null,
    @ColumnInfo(name = "isDeleted") var isDeleted: Boolean? = null
)