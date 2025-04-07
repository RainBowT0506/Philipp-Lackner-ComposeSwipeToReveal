package com.plcoding.composeswipetoreveal

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 可重複使用的 Composable，代表一個操作按鈕（例如：刪除、分享、寄信等）
 * 可自定義背景顏色、Icon、點擊事件等。
 *
 * @param onClick 點擊此按鈕時要執行的 Lambda 函式
 * @param backgroundColor 按鈕的背景顏色
 * @param icon 要顯示的圖示 (ImageVector)
 * @param modifier 外部傳入的修飾符（可選）
 * @param contentDescription 圖示的內容描述（用於無障礙輔助，預設為 null）
 * @param tint 圖示的顏色，預設為白色
 */
@Composable
fun ActionIcon(
    onClick: () -> Unit,
    backgroundColor: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.White
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(backgroundColor)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}