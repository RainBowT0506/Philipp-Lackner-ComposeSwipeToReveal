package com.plcoding.composeswipetoreveal

/**
 * 資料類別，用來表示單一聯絡人項目的 UI 狀態。
 *
 * @param id 聯絡人的唯一識別編號
 * @param name 聯絡人名稱
 * @param isOptionsRevealed 此聯絡人是否已展開操作選項（例如：刪除、分享...）
 */
data class ContactUi(
    val id: Int,
    val name: String,
    val isOptionsRevealed: Boolean
)
