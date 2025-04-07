### Custom My SwipeableItem

![image](https://github.com/user-attachments/assets/8bb0fe38-b13b-4a91-b4b7-dc20cb158632)

![image](https://github.com/user-attachments/assets/82f6da3b-5415-474a-ad3a-4870666ca297)


### 初始化專案與刪除預設內容
- 開啟一個空的 Jetpack Compose 專案。
- 移除預設的 `Greeting` Composable 與 Preview。
- 不需要額外的相依套件，Material 3 已內建所需的功能。

---

### 建立刪除背景 Composable
- 建立一個紅色背景搭配刪除圖示的 Composable。
- 根據 `DismissDirection` 判斷是否顯示紅色背景或透明。
- 使用 `Box` 搭配 `Modifier.fillMaxSize()` 和 `padding(16.dp)`。
- 使用 `Icons.Default.Delete` 並設為白色，位置靠右。

---

### 建立通用的 SwipeToDelete 容器
- 使用泛型 `<T>` 實作可重複使用的容器。
- 參數包含：item、onDelete Lambda、動畫時間（預設 500ms）、Composables 的內容。
- 用 `remember` 建立 `DismissState` 與 `isRemoved` 狀態。

---

### 處理滑動刪除邏輯
- 在 `confirmValueChange` 中判斷是否滑到左側 (`DismissedToStart`)。
- 若是，將 `isRemoved` 設為 true 並返回 true，代表確認刪除。
- 只允許向左滑動 (`DismissDirection.EndToStart`)。

---

### 顯示刪除動畫
- 使用 `AnimatedVisibility` 控制 Composable 是否顯示。
- 動畫使用 `shrinkVertically` 搭配 `fadeOut`。
- 動畫方向設定為 `Alignment.Top`，動畫時間由參數決定。

---

### 延遲觸發刪除 Callback
- 使用 `LaunchedEffect` 監聽 `isRemoved` 變化。
- 當 `isRemoved` 為 true 時，延遲動畫時間後呼叫 `onDelete`。

---

### 在 LazyColumn 中使用 SwipeToDelete 容器
- 建立可變 `programmingLanguages` 清單，包含數個語言字串。
- 使用 `LazyColumn` 搭配 `items` 顯示資料。
- 每個項目使用 `SwipeToDeleteContainer` 包裹。
- `onDelete` 中從資料清單中移除對應項目。

---

### 修正刪除多個項目的問題
- 問題原因：`LazyColumn` 未設定唯一 key 導致項目錯亂。
- 解法：在 `items` 區塊中加入 `key = { it }`，確保每項唯一識別。
- 若資料結構更複雜，可使用資料的唯一 ID 作為 key。

---

### 使用建議
- 完成後可將這兩個 Composable 拿去專案中重複使用。
- 支援任意資料型別，靈活實用。
- 官方推薦避免常見 Jetpack Compose 錯誤，可參考作者提供的免費 PDF。

# Terminology
- **Jetpack Compose**：Google 推出的現代化 UI 工具，用於聲明式建構 Android 使用者介面。  
- **Composable**：Jetpack Compose 中的核心概念，代表一個可以組合的 UI 函式。  
- **Modifier**：用於修改 UI 元件外觀或行為的屬性容器。  
- **LazyColumn**：一種高效能的垂直列表容器，只會繪製可視區域內的項目。  
- **State**：Jetpack Compose 中的可觀察資料模型，改變會自動觸發 UI 更新。  
- **MutableState**：可變狀態變數，可用於追蹤並響應資料變化。  
- **remember**：用於保留組件狀態於重組過程中。  
- **dismissState**：控制與監聽滑動刪除狀態的狀態容器。  
- **SwipeToDismiss**：Material3 提供的可滑動刪除組件。  
- **DismissDirection**：表示滑動刪除的方向（例如 Start、End）。  
- **DismissValue**：表示目前滑動的狀態（如 Default、DismissedToStart）。  
- **AnimatedVisibility**：用來實現進入與離開動畫的組件。  
- **shrinkVertically**：縮放動畫，垂直方向縮小項目大小。  
- **fadeOut**：淡出動畫效果，使元件逐漸透明消失。  
- **tween**：用於定義動畫的插值器及持續時間。  
- **LaunchEffect**：組件用來在組成期間啟動協程的 API。  
- **delay**：協程中的非阻塞延遲函數。  
- **ImageVector**：代表向量圖標資源的類型。  
- **Icons.Default.Delete**：Jetpack Compose 提供的內建刪除圖標。  
- **MaterialTheme**：提供應用整體樣式的主題結構。  
- **colorScheme**：Material3 中的色彩配置存取器。  
- **Alignment.CenterEnd**：組件對齊方式，垂直置中，水平靠右。  
- **Box**：基本的 UI 容器，用來重疊子項目。  
- **ContentAlignment**：控制子項目在容器內的對齊方式。  
- **PaddingValues**：設定元件內部留白的資料結構。  
- **Modifier.fillMaxSize()**：設定組件寬高充滿可用空間。  
- **Modifier.fillMaxWidth()**：設定寬度填滿父容器。  
- **Modifier.background()**：設定背景顏色或圖案。  
- **Modifier.padding()**：設定內距。  
- **mutableStateListOf()**：建立可觀察且可變的列表。  
- **items()**：LazyColumn 用來產生子項目的函式。  
- **key()**：為 LazyColumn 中的每個項目指定唯一鍵值，避免重組錯亂。  
- **Composable Lambda**：以 Lambda 表達式形式實作的 Composable 函式。  
- **Generic Type Parameter**：泛型參數，允許函式支援多種類型。  
- **Lambda 表達式**：一種簡潔的函數表示法，可作為參數傳遞。  
- **Surface**：Material3 提供的基礎 UI 容器，應用背景、陰影等屬性。  
- **@OptIn**：告知編譯器使用實驗性 API。  
- **@Composable**：標註函式為 Composable，使其能夠組合成 UI。  
- **ExperimentalMaterial3Api**：表示正在使用尚未穩定的 Material3 API。  
- **Icon()**：顯示向量圖示的組件。  
- **ContentDescription**：提供無障礙輔助說明的文字描述。  
- **Color.Red / Color.Transparent**：定義顏色常數。  
- **Color.White**：白色，用作圖標的顏色對比。  
- **ViewModel**：用來儲存與管理 UI 相關資料的類別。  
- **Recomposition**：Compose 中重新執行 Composable 函式以反映狀態變更的過程。  
- **List.remove()**：從列表中移除特定項目的方法。  
- **ShrinkTowards**：控制動畫收縮方向的參數。  
- **DismissContent**：滑動項目內容的主體區域。  
- **Background**：在滑動動作中出現的背景圖層。  
- **onDelete Lambda**：滑動刪除完成後觸發的回呼函數。  
- **延遲觸發刪除**：等動畫完成後才刪除資料的策略。  
- **AnimationSpec**：動畫的規格參數，定義其速度、曲線等。  
- **實驗性 API**：尚未穩定的 API，可能會變動。  
- **List Key 冲突**：未指定唯一鍵值時造成的錯誤重組行為。  
- **UI Animation**：使用動畫提升使用者體驗與互動感。
