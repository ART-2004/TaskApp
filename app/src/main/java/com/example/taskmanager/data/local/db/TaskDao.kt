package com.example.taskmanager.data.local.db

import androidx.room.*
import com.example.taskmanager.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * FROM TaskMode ORDER BY title DESC")
    fun getAllTaskByAlphabetAz(): List<Task?>?

    @Query("SELECT * FROM TaskMode ORDER BY title ASC")
    fun getAllTaskByAlphabetZa(): List<Task?>?

    @Query("SELECT * FROM TaskMode ORDER BY id DESC")
    fun getAllTaskByDate(): List<Task?>?
}