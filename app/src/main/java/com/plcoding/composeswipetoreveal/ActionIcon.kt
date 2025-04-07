package com.plcoding.composeswipetoreveal

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.composeswipetoreveal.ui.theme.DeleteBgColor
import com.plcoding.composeswipetoreveal.ui.theme.PinBgColor
import com.plcoding.composeswipetoreveal.ui.theme.UnpinBgColor

/**
 * 可重複使用的 Composable，代表一個操作按鈕（例如：刪除、分享、寄信等）
 * 可自定義背景顏色、圖示資源、點擊事件等。
 *
 * @param onClick 點擊此按鈕時要執行的 Lambda 函式
 * @param backgroundColor 按鈕的背景顏色
 * @param resId 要顯示的圖片資源 ID（@DrawableRes）
 * @param modifier 外部傳入的修飾符（可選）
 * @param contentDescription 圖示的內容描述（用於無障礙輔助，預設為 null）
 * @param tint 圖示的顏色，預設為白色（不一定所有圖片都會套用）
 */
@Composable
fun ActionIcon(
    onClick: () -> Unit,
    backgroundColor: Color,
    @DrawableRes resId: Int,
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
            painter = painterResource(id = resId),
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

@Composable
fun PinActionBlock(onPinClick: () -> Unit) {
    ActionIcon(
        onClick = {
            onPinClick.invoke()
        },
        backgroundColor = PinBgColor,
        resId = R.drawable.ic_pin,
        modifier = Modifier.fillMaxHeight()
    )
}

@Composable
fun UnpinActionBlock(onUnpinClick: () -> Unit) {
    ActionIcon(
        onClick = {
            onUnpinClick.invoke()
        },
        backgroundColor = UnpinBgColor,
        resId = R.drawable.ic_unpin,
        modifier = Modifier.fillMaxHeight()
    )
}

@Composable
fun DeleteActionBlock(onDeleteClick: () -> Unit) {
    ActionIcon(
        onClick = {
            onDeleteClick.invoke()
        },
        backgroundColor = DeleteBgColor,
        resId = R.drawable.ic_delete,
        modifier = Modifier.fillMaxHeight()
    )
}

@Preview(showBackground = true)
@Composable
fun ActionIconPreview() {
    Column(modifier = Modifier.padding(20.dp)) {
        PinActionBlock({})

        Spacer(modifier = Modifier.height(20.dp))

        UnpinActionBlock({})

        Spacer(modifier = Modifier.height(20.dp))

        DeleteActionBlock({})
    }
}