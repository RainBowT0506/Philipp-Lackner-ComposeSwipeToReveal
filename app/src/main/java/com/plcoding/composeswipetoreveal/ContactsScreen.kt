package com.plcoding.composeswipetoreveal

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.plcoding.composeswipetoreveal.ui.theme.PinBgColor
import com.plcoding.composeswipetoreveal.ui.theme.UnpinBgColor

@Composable
fun ContactScreen() {
    val context = LocalContext.current
    val contacts = remember {
        mutableStateListOf(
            *(1..100).map {
                ContactUi(
                    id = it,
                    name = "Contact $it",
                    isOptionsRevealed = false
                )
            }.toTypedArray()
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(
            items = contacts,
        ) { index, contact ->

            // 使用 SwipeableItemWithActions 包裝每個聯絡人項目
            // 支援向右滑動展開操作按鈕列（刪除 / 寄信 / 分享）
            SwipeableItemWithActions(
                isRevealed = contact.isOptionsRevealed,
                onExpanded = {
                    // 當滑動展開時，更新該聯絡人為展開狀態
                    contacts[index] = contact.copy(isOptionsRevealed = true)
                },
                onCollapsed = {
                    // 當滑動收合時，更新該聯絡人為未展開
                    contacts[index] = contact.copy(isOptionsRevealed = false)
                },

                // 定義滑動展開後會顯示的動作按鈕們（排列在 Row 裡）
                actions = {
                    PinActionBlock{
                        Toast.makeText(
                            context,
                            "Contact ${contact.id} was deleted.",
                            Toast.LENGTH_SHORT
                        ).show()
                        contacts.remove(contact)
                    }
                    UnpinActionBlock{
                        contacts[index] = contact.copy(isOptionsRevealed = false)
                        Toast.makeText(
                            context,
                            "Contact ${contact.id} was sent an email.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
            ) {
                Text(
                    text = "Contact ${contact.id}",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
