Teknasyon Scenarios
====================
Created by ercan kirbiyik


 Click element "APIDemosDevam"
 Click element "APIDemosTamam"

Scenario 1
---------
* Click element "APIDemosApp"
* Click element "APIDemosActionBar"
* Click element "APIDemosActionBarTabs"
* Click element "APIDemosToggleTabMode"
* Click the "APIDemosAddNewTab" element "3" times
* "APIDemosTab0" elementinin görünürlüğü kontrol edilir
* Check that the "APIDemosTab0" element contains the text "TAB 0"
* Check if element "APIDemosTab1" exist
* Check that the "APIDemosTab1" element contains the text "TAB 1"
* Check if element "APIDemosTab2" exist
* Check that the "APIDemosTab2" element contains the text "TAB 2"
* Click element "APIDemosRemoveLastTab"
* Check that the "APIDemosTab2" element is not visible
* Click element "APIDemosRemoveAllTabs"
* Check that the "APIDemosTabList" element is not visible


Scenario 2
----------
* Click element "APIDemosApp"
* Click element "APIDemosActivity"
* Click element "APIDemosCustomTitle"
* Check that the "APIDemosLeftTextEdit" element contains the text "Left is best"
* Check that the "APIDemosRightTextEdit" element contains the text "Right is always right"
* Check that the "APIDemosActivityTabText" element contains the text "Left is best"
* Find element by "APIDemosLeftTextEdit" clear and send keys "Left Test123"
* Click element "APIDemosChangeLeftButton"
* Find element by "APIDemosRightTextEdit" clear and send keys "Right Test123"
* Click element "APIDemosChangeRightButton"
* Check that the "APIDemosLeftTextEdit" element contains the text "Left Test123"
* Check that the "APIDemosRightTextEdit" element contains the text "Right Test123"
* Check that the "APIDemosActivityTabText" element contains the text "Left Test123"


Scenario 3
----------
* Click element "APIDemosApp"
* Click element "APIDemosAlertDialogs"
* Click element "APIDemosListDialog"
* Click element "APIDemosCommandTwo"
* Check that the "APIDemosPopupMessage" element contains the text "You selected: 1 , Command two"


Scenario 4
----------
* Click element "APIDemosApp"
* Click element "APIDemosDeviceAdmin"
* Click element "APIDemosDeviceAdminGeneral"
* Click element "APIDemosDeviceAdminEnableAdmin"
* Click element "APIDemosDeviceAdminEnableAdminActivateButton"
 Check that the "APIDemosPopupMessage" element contains the text "Sample Device Admin: enabled"
* Check element "APIDemosDeviceAdminGeneral" is active
* Check element "APIDemosDeviceAdminEnableAdminRadioButton" is active


Scenario 5
----------
* Click element "APIDemosApp"
* Click element "APIDemosFragment"
* Click element "APIDemosFragmentContextMenu"
Step çalışmadığı için fail alıyor, detaylar fonksiyona yazıldı
* Press and hold the element "APIDemosLongPressButton" for "3" seconds
* Check if element "APIDemosLongPressMenuA" exist
* Check if element "APIDemosLongPressMenuB" exist


Scenario 6
----------
* Click element "APIDemosApp"
* Click element "APIDemosFragment"
* Click element "APIDemosHideAndShow"
* Check for "2" "APIDemosHide" elements
* Check for "2" "APIDemosHideAndShowTextBoxes" elements
* Click element "APIDemosSecondHideButton"
* Check if element "APIDemosShowButton" exist
* Check for "1" "APIDemosHideAndShowTextBoxes" elements
* Click element "APIDemosShowButton"
* Check for "2" "APIDemosHideAndShowTextBoxes" elements
* Check if element "APIDemosSecondHideButton" exist


Scenario 7
----------
* Click element "APIDemosApp"
* Click element "APIDemosNotification"
* Click element "APIDemosIncomingMessage"
* Click element "APIDemosShowNotify"
* Open the notification bar area
* Check if element "APIDemosNotification" exist
* Find element by "APIDemosNotification" and save text as "NotificationText"
* Click element "APIDemosNotification"
* Check that the element "APIDemosIncomingMessageView" contains the saved attribute "NotificationText"


Scenario 8
----------
* Click element "APIDemosApp"
* Click element "APIDemosViews"
* Swipe down "2" times
* Click element "APIDemosTabs"
* Click element "APIDemos5.Scrollable"
* Check if element "APIDemosTAB30" exist
