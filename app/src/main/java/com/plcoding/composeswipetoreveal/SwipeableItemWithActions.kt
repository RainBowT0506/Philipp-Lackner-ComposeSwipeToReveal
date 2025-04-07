package com.plcoding.composeswipetoreveal

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * 一個可以左右滑動的可組合項目，滑動後會顯示出一組操作按鈕。
 *
 * 常見應用像是：在列表中對某個項目滑動，顯示「刪除」、「分享」、「編輯」等按鈕。
 *
 * @param isRevealed 控制是否展開操作列
 * @param actions 操作列中的按鈕內容（Composable RowScope）
 * @param modifier 外部可套用的 Modifier
 * @param onExpanded 當滑動超過一半、展開時要執行的動作（如更新狀態）
 * @param onCollapsed 當滑動不足一半、收合時要執行的動作
 * @param content 主內容區塊，例如聯絡人名稱、清單項目等
 */
@Composable
fun SwipeableItemWithActions(
    isRevealed: Boolean,
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    onExpanded: () -> Unit = {},
    onCollapsed: () -> Unit = {},
    content: @Composable () -> Unit
) {

    // 儲存操作按鈕列的寬度（用於決定滑動最大距離）
    var contextMenuWidth by remember {
        mutableFloatStateOf(0f)
    }

    // 用於動畫處理的偏移值（橫向滑動距離）
    val offset = remember {
        Animatable(initialValue = 0f)
    }

    // coroutine scope，用來執行動畫
    val scope = rememberCoroutineScope()

    // 根據 isRevealed 控制展開或收起動畫
    LaunchedEffect(key1 = isRevealed, contextMenuWidth) {
        if (isRevealed) {
            // 展開到最大寬度
            offset.animateTo(contextMenuWidth)
        } else {
            // 收回到原位
            offset.animateTo(0f)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {

        // 底層操作列，放置按鈕（例如刪除、分享）
        Row(
            modifier = Modifier
                .onSizeChanged {
                    contextMenuWidth = it.width.toFloat()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }

        // 上層內容：例如聯絡人資料卡片，可滑動覆蓋在按鈕上
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offset.value.roundToInt(), 0) }
                .pointerInput(contextMenuWidth) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset = (offset.value + dragAmount)
                                    .coerceIn(0f, contextMenuWidth)
                                offset.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value >= contextMenuWidth / 2f -> {
                                    scope.launch {
                                        offset.animateTo(contextMenuWidth)
                                        onExpanded()
                                    }
                                }
                                else -> {
                                    scope.launch {
                                        offset.animateTo(0f)
                                        onCollapsed()
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            content()
        }
    }
}