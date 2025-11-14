CREATE DATABASE IF NOT EXISTS courseDB_localization CHARACTER SET utf8mb4 COLLATE
utf8mb4_unicode_ci;

use courseDB_localization;

-- Stors localized text for UI

CREATE TABLE IF NOT EXISTS localization_strings(
    id INT AUTO_INCREMENT PRIMARY KEY,
    `key` VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    language VARCHAR(10) NOT NULL
);

--English
INSERT INTO localization_strings (`key`, value, language) VALUES
("main.frontpageTitle", 'Homepage', 'en'),
("main.softwareTitle", 'Attendance Software', 'en'),
("main.profileButton", 'PROFILE', 'en'),
("main.coursesButton", 'COURSES', 'en'),
("main.adminButton", 'ADMIN PANEL', 'en'),

("profile.roleLabel", 'Degree', 'en'),
("profile.participatingIn", 'Enrolled in courses', 'en'),
("staff.status", 'Staff', 'en'),
("staff.role", 'Role', 'en'),

("login.username", 'Username', 'en'),
("login.password", 'Password', 'en'),
("login.failed", 'Login failed: Incorrect username or password.', 'en'),
("login.button", 'Sign in', 'en'),
("login.languagemenu", 'Language', 'en'),

("login.englishmenu", 'English', 'en'),
("login.finnishmenu", 'Finnish', 'en'),
("login.japanesemenu", 'Japanese', 'en'),
("login.persianmenu", 'Persian', 'en'),

("course.button", 'Attendance', 'en'),

("attendance.menubutton", 'Attendance', 'en'),
("attendance.present", 'Present', 'en'),
("attendance.absent", 'Absent', 'en'),

("admin.viewstaff", 'View staff', 'en'),
("admin.createstaff", 'Create staff', 'en'),
("admin.modifystaff", 'Edit staff', 'en'),

("admin.viewstudent", 'View students', 'en'),
("admin.createstudent", 'Create student', 'en'),
("admin.modifystudent", 'Edit student', 'en'),

("admin.title", 'Admin Panel', 'en'),
("admin.viewcourse", 'View course', 'en'),
("admin.createcourse", 'Create course', 'en'),
("admin.modifycourse", 'Edit course', 'en'),

("admin.viewattendance", 'View attendance', 'en'),
("admin.modifyattendance", 'Edit attendance', 'en'),

("admin.viewdegree", 'View degrees', 'en'),
("admin.createdegree", 'Create degree', 'en'),

("admin.savebutton", 'Save', 'en'),
("admin.cancelbutton", 'Cancel', 'en'),
("admin.deletebutton", 'Delete', 'en'),
("admin.modifybutton", 'Edit', 'en'),
("admin.closebutton", 'Close', 'en'),
("admin.refreshbutton", 'Refresh', 'en'),

("create", 'Create', 'en'),
("cancel", 'Cancel', 'en'),
("useractive", 'User is active', 'en'),

("createstudent.name", 'Name (First name, Last name)', 'en'),
("createstudent.studentid", 'Student ID', 'en'),
("createstudent.studentdegree", 'Student degree', 'en'),
("createstudent.password", 'Password', 'en'),

("createcourse.coursename", 'Course name', 'en'),
("createcourse.topic", 'Course topic', 'en'),
("createcourse.description", 'Course description', 'en'),
("createcourse.minattend", 'Minimum attendance', 'en'),
("createcourse.maxattend", 'Maximum attendance', 'en'),
("createcourse.codeattend", 'Attendance code', 'en'),
("createcourse.coursactive", 'Course is active', 'en'),
("createcourse.isattend", 'Attendance required', 'en'),
("createcourse.attendcode", 'Attendance code', 'en'),

("createstaff.name", 'Staff name', 'en'),
("createstaff.role", 'Staff role', 'en'),
("createstaff.password", 'Password', 'en'),
("createstaff.isadmin", 'Is admin', 'en'),

("createdegree.name", 'Degree name', 'en'),
("createdegree.ects", 'ECTS credits', 'en'),

("tableview.id", 'ID', 'en'),
("tableview.studId", 'Student ID', 'en'),
("tableview.courId", 'Course ID', 'en'),
("tableview.staffId", 'Staff ID', 'en'),
("tableview.ects", 'ECTS', 'en'),
("tableview.name", 'Name', 'en'),
("tableview.role", 'Role', 'en'),
("tableview.admin", 'Is admin', 'en'),
("tableview.degree", 'Degree', 'en'),
("tableview.topic", 'Topic', 'en'),
("tableview.desc", 'Description', 'en'),
("tableview.attenAv", 'Attendance available', 'en'),
("tableview.attenKey", 'Attendance password', 'en'),
("tableview.minAtt", 'Minimum attendance', 'en'),
("tableview.maxAtt", 'Maximum course length', 'en'),
("tableview.active", 'Active', 'en'),
("tableview.handl", 'Handled', 'en'),
("tableview.curr", 'Amount', 'en'),
("tableview.time", 'Created', 'en'),

("deletion.noselect", 'No item selected', 'en'),
("deletion.errorfetch", 'An error occurred while fetching row ID. Error:', 'en'),
("deletion.confirmtitle", 'Confirm deletion', 'en'),
("deletion.confirm", 'Are you sure you want to delete this entry?', 'en'),

("modif.noselect", 'No item selected', 'en'),
("modif.errorfetch", 'An error occurred while fetching row ID. Error:', 'en'),
("modif.errordial", 'Error opening dialog', 'en'),
("modif.updateBtn", 'Update', 'en'),
("modif.password", 'Change password. Leave blank to keep current password.', 'en'),
("modif.title", 'Edit entry', 'en');

-- Finnish
INSERT INTO localization_strings (`key`, value, language) VALUES
("main.frontpageTitle", 'Etusivu', 'fi'),
("main.softwareTitle", 'Läsnäolo-ohjelmisto', 'fi'),
("main.profileButton", 'PROFIILI', 'fi'),
("main.coursesButton", 'KURSSIT', 'fi'),
("main.adminButton", 'HALLINTAPANEELI', 'fi'),

("profile.roleLabel", 'Tutkinto', 'fi'),
("profile.participatingIn", 'Ilmoittautunut kursseille', 'fi'),
("staff.status", 'Henkilöstö', 'fi'),
("staff.role", 'Rooli', 'fi'),

("login.username", 'Käyttäjätunnus', 'fi'),
("login.password", 'Salasana', 'fi'),
("login.failed", 'Kirjautuminen epääonnistui: Väärä salasana tai käyttäjätunnus!', 'fi'),
("login.button", 'Kirjaudu sisään', 'fi'),
("login.languagemenu", 'Kieli', 'fi'),

("login.englishmenu", 'Englanti', 'fi'),
("login.finnishmenu", 'Suomi', 'fi'),
("login.japanesemenu", 'Japani', 'fi'),
("login.persianmenu", 'Persia', 'fi'),

("course.button", 'Läsnäolo', 'fi'),
("attendance.menubutton", 'Läsnäolo', 'fi'),
("attendance.present", 'Läsnä', 'fi'),
("attendance.absent", 'Poissa', 'fi'),

("admin.title", 'Hallintapaneeli', 'fi'),
("admin.viewstaff", 'Katso henkilöstöä', 'fi'),
("admin.createstaff", 'Luo henkilöstö', 'fi'),
("admin.modifystaff", 'Muokkaa henkilöstöä', 'fi'),

("admin.viewstudent", 'Katso opiskelijoita', 'fi'),
("admin.createstudent", 'Luo opiskelija', 'fi'),
("admin.modifystudent", 'Muokkaa opiskelijaa', 'fi'),

("admin.viewcourse", 'Katso kurssia', 'fi'),
("admin.createcourse", 'Luo kurssi', 'fi'),
("admin.modifycourse", 'Muokkaa kurssia', 'fi'),

("admin.viewattendance", 'Katso läsnäolot', 'fi'),
("admin.modifyattendance", 'Muokkaa läsnäoloja', 'fi'),

("admin.viewdegree", 'Näytä tutkinnot', 'fi'),
("admin.createdegree", 'Luo tutkinto', 'fi'),

("admin.savebutton", 'Tallenna', 'fi'),
("admin.cancelbutton", 'Peruuta', 'fi'),
("admin.deletebutton", 'Poista', 'fi'),
("admin.modifybutton", 'Muokkaa', 'fi'),
("admin.closebutton", 'Sulje', 'fi'),
("admin.refreshbutton", 'Päivitä', 'fi'),

("create", 'Luo', 'fi'),
("cancel", 'Peruuta', 'fi'),
("useractive", 'Käyttäjä on aktiivinen', 'fi'),

("createstudent.name", 'Nimi (Etunimi, sukunimi)', 'fi'),
("createstudent.studentid", 'Opiskelijanumero', 'fi'),
("createstudent.studentdegree", 'Opiskelijan tutkinto', 'fi'),
("createstudent.password", 'Salasana', 'fi'),

("createcourse.coursename", 'Kurssin nimi', 'fi'),
("createcourse.topic", 'Kurssin aihe', 'fi'),
("createcourse.description", 'Kurssin kuvaus', 'fi'),
("createcourse.minattend", 'Vähimmäisläsnäolo', 'fi'),
("createcourse.maxattend", 'Enimmäisläsnäolo', 'fi'),
("createcourse.codeattend", 'Läsnäolokoodi', 'fi'),
("createcourse.coursactive", 'Kurssi on aktiivinen', 'fi'),
("createcourse.isattend", 'Läsnäolo vaaditaan', 'fi'),
("createcourse.attendcode", 'Läsnäolokoodi', 'fi'),

("createstaff.name", 'Henkilökunnan nimi', 'fi'),
("createstaff.role", 'Henkilökunnan rooli', 'fi'),
("createstaff.password", 'Salasana', 'fi'),
("createstaff.isadmin", 'On ylläpitäjä', 'fi'),

("createdegree.name", 'Tutkinnon nimi', 'fi'),
("createdegree.ects", 'Opintopiste', 'fi'),

("tableview.id", 'ID', 'fi'),
("tableview.studId", 'Oppilas ID', 'fi'),
("tableview.courId", 'Kurssin ID', 'fi'),
("tableview.staffId", 'Opettajan ID', 'fi'),
("tableview.ects", 'Opintopiste', 'fi'),
("tableview.name", 'Nimi', 'fi'),
("tableview.role", 'Rooli', 'fi'),
("tableview.admin", 'On ylläpitäjä', 'fi'),
("tableview.degree", 'Tutkinto', 'fi'),
("tableview.topic", 'Aihe', 'fi'),
("tableview.desc", 'Kuvaus', 'fi'),
("tableview.attenAv", 'Läsnäolo saatavilla', 'fi'),
("tableview.attenKey", 'Läsnäolon salasana', 'fi'),
("tableview.minAtt", 'Vähimmäisläsnäolo', 'fi'),
("tableview.maxAtt", 'Kurssin pituus', 'fi'),
("tableview.active", 'Aktiivinen', 'fi'),
("tableview.handl", 'Käsitelty', 'fi'),
("tableview.curr", 'Määrä', 'fi'),
("tableview.time", 'Luotu', 'fi'),

("deletion.noselect", 'Ei valittua kohdetta', 'fi'),
("deletion.errorfetch", 'Virhe haettaessa rivin tunnusta. Virhe:', 'fi'),
("deletion.confirmtitle", 'Vahvista poisto', 'fi'),
("deletion.confirm", 'Oletko varma, että haluat poistaa tämän tietueen?', 'fi'),

("modif.noselect", 'Ei valittua kohdetta', 'fi'),
("modif.errorfetch", 'Virhe haettaessa rivin tunnusta. Virhe:', 'fi'),
("modif.errordial", 'Virhe avattaessa valintaikkunaa', 'fi'),
("modif.updateBtn", 'Päivitä', 'fi'),
("modif.password", 'Vaihda salasana. Jätä tyhjäksi, jos haluat säilyttää nykyisen salasanan', 'fi'),
("modif.title", 'Muokkaa tietuetta', 'fi');

-- Japanese
INSERT INTO localization_strings (`key`, value, language) VALUES
("main.frontpageTitle", 'ホーム', 'ja'),
("main.softwareTitle", '出席管理ソフト', 'ja'),
("main.profileButton", 'プロフィール', 'ja'),
("main.coursesButton", 'コース', 'ja'),
("main.adminButton", '管理パネル', 'ja'),

("profile.roleLabel", '専攻', 'ja'),
("profile.participatingIn", '参加中', 'ja'),
("staff.status", 'スタッフ', 'ja'),
("staff.role", '役割', 'ja'),

("login.username", 'ユーザー名', 'ja'),
("login.password", 'パスワード', 'ja'),
("login.failed", 'ログインに失敗しました: ユーザー名またはパスワードが正しくありません。', 'ja'),
("login.button", 'サインイン', 'ja'),
("login.languagemenu", '言語', 'ja'),

("login.englishmenu", '英語', 'ja'),
("login.finnishmenu", 'フィンランド語', 'ja'),
("login.japanesemenu", '日本語', 'ja'),
("login.persianmenu", 'ペルシア語', 'ja'),

("course.button", '出席', 'ja'),

("attendance.menubutton", '出席', 'ja'),
("attendance.present", '出席', 'ja'),
("attendance.absent", '欠席', 'ja'),

("admin.viewstaff", 'スタッフを表示', 'ja'),
("admin.createstaff", 'スタッフを作成', 'ja'),
("admin.modifystaff", 'スタッフを編集', 'ja'),

("admin.viewstudent", '学生を表示', 'ja'),
("admin.createstudent", '学生を作成', 'ja'),
("admin.modifystudent", '学生を編集', 'ja'),

("admin.title", '管理パネル', 'ja'),
("admin.viewcourse", 'コースを表示', 'ja'),
("admin.createcourse", 'コースを作成', 'ja'),
("admin.modifycourse", 'コースを編集', 'ja'),

("admin.viewattendance", '出席を表示', 'ja'),
("admin.modifyattendance", '出席を編集', 'ja'),

("admin.viewdegree", '度数を表示', 'ja'),
("admin.createdegree", '学位を取得', 'ja'),

("admin.savebutton", '保存', 'ja'),
("admin.cancelbutton", 'キャンセル', 'ja'),
("admin.deletebutton", '削除', 'ja'),
("admin.modifybutton", '編集', 'ja'),
("admin.closebutton", 'ブラケット', 'ja'),
("admin.refreshbutton", 'アップデート', 'ja'),

("create", '作成', 'ja'),
("cancel", 'キャンセル', 'ja'),
("useractive", 'ユーザーは有効です', 'ja'),

("createstudent.name", '氏名（名、姓）', 'ja'),
("createstudent.studentid", '学生ID', 'ja'),
("createstudent.studentdegree", '学生の学位', 'ja'),
("createstudent.password", 'パスワード', 'ja'),

("createcourse.coursename", 'コース名', 'ja'),
("createcourse.topic", 'コースのトピック', 'ja'),
("createcourse.description", 'コースの説明', 'ja'),
("createcourse.minattend", '最低出席数', 'ja'),
("createcourse.maxattend", '最大出席数', 'ja'),
("createcourse.codeattend", '出席コード', 'ja'),
("createcourse.coursactive", 'コースは有効です', 'ja'),
("createcourse.isattend", '出席必須', 'ja'),
("createcourse.attendcode", 'レスナオロコディ', 'ja'),

("createstaff.name", 'スタッフ名', 'ja'),
("createstaff.role", 'スタッフの役割', 'ja'),
("createstaff.password", 'パスワード', 'ja'),
("createstaff.isadmin", '管理者です', 'ja'),

("createdegree.name", '学位名', 'ja'),
("createdegree.ects", '欧州単位互換システム', 'ja'),

("tableview.id", 'ID', 'ja'),
("tableview.studId", '学生 ID', 'ja'),
("tableview.courId", 'コース ID', 'ja'),
("tableview.staffId", 'スタッフ ID', 'ja'),
("tableview.ects", '欧州単位互換システム', 'ja'),
("tableview.name", '名前', 'ja'),
("tableview.role", '役割', 'ja'),
("tableview.admin", '管理者です', 'ja'),
("tableview.degree", '程度', 'ja'),
("tableview.topic", 'トピック', 'ja'),
("tableview.desc", '説明', 'ja'),
("tableview.attenAv", '参加可能', 'ja'),
("tableview.attenKey", '出席パスワード', 'ja'),
("tableview.minAtt", '最低出席者数', 'ja'),
("tableview.maxAtt", '最大出席者数', 'ja'),
("tableview.active", 'アクティブ', 'ja'),
("tableview.handl", '処理済み', 'ja'),
("tableview.curr", '現在', 'ja'),
("tableview.time", '作成場所', 'ja'),

("deletion.noselect", '項目が選択されていません', 'ja'),
("deletion.errorfetch", '行IDの取得中にエラーが発生しました。エラー:', 'ja'),
("deletion.confirmtitle", '削除の確認', 'ja'),
("deletion.confirm", 'このエントリを削除してもよろしいですか？', 'ja'),

("modif.noselect", '項目が選択されていません', 'ja'),
("modif.errorfetch", '行IDの取得中にエラーが発生しました。エラー:', 'ja'),
("modif.errordial", 'ダイアログを開く際にエラーが発生しました', 'ja'),
("modif.updateBtn", '更新', 'ja'),
("modif.password", 'パスワードを変更します。現在のパスワードを保持する場合は空白のままにしてください', 'ja'),
("modif.title", 'エントリを編集', 'ja');

-- Persian
INSERT INTO localization_strings (`key`, value, language) VALUES
("main.frontpageTitle", 'صفحه اصلی', 'fa'),
("main.softwareTitle", 'نرم‌افزار حضور و غیاب', 'fa'),
("main.profileButton", 'پروفایل', 'fa'),
("main.coursesButton", 'دوره‌ها', 'fa'),
("main.adminButton", 'پنل مدیریت', 'fa'),

("profile.roleLabel", 'رشته', 'fa'),
("profile.participatingIn", 'ثبت‌نام شده در دوره‌ها', 'fa'),
("staff.status", 'کارکنان', 'fa'),
("staff.role", 'نقش', 'fa'),

("login.username", 'نام کاربری', 'fa'),
("login.password", 'رمز عبور', 'fa'),
("login.failed", 'ورود ناموفق: نام کاربری یا رمز عبور اشتباه است.', 'fa'),
("login.button", 'ورود', 'fa'),
("login.languagemenu", 'زبان', 'fa'),

("login.englishmenu", 'انگلیسی', 'fa'),
("login.finnishmenu", 'فنلاندی', 'fa'),
("login.japanesemenu", 'ژاپنی', 'fa'),
("login.persianmenu", 'فارسی', 'fa'),

("course.button", 'حضور', 'fa'),

("attendance.menubutton", 'حضور', 'fa'),
("attendance.present", 'حاضر', 'fa'),
("attendance.absent", 'غایب', 'fa'),

("admin.viewstaff", 'مشاهده کارکنان', 'fa'),
("admin.createstaff", 'ایجاد کارمند', 'fa'),
("admin.modifystaff", 'ویرایش کارمند', 'fa'),

("admin.viewstudent", 'مشاهده دانشجویان', 'fa'),
("admin.createstudent", 'ایجاد دانشجو', 'fa'),
("admin.modifystudent", 'ویرایش دانشجو', 'fa'),

("admin.title", 'پنل مدیریت', 'fa'),
("admin.viewcourse", 'مشاهده دوره', 'fa'),
("admin.createcourse", 'ایجاد دوره', 'fa'),
("admin.modifycourse", 'ویرایش دوره', 'fa'),

("admin.viewattendance", 'مشاهده حضور و غیاب', 'fa'),
("admin.modifyattendance", 'ویرایش حضور و غیاب', 'fa'),

("admin.viewdegree", 'مشاهده مدارک', 'fa'),
("admin.createdegree", 'ایجاد مدرک', 'fa'),

("admin.savebutton", 'ذخیره', 'fa'),
("admin.cancelbutton", 'لغو', 'fa'),
("admin.deletebutton", 'حذف', 'fa'),
("admin.modifybutton", 'ویرایش', 'fa'),
("admin.closebutton", 'بستن', 'fa'),
("admin.refreshbutton", 'به‌روزرسانی', 'fa'),

("create", 'ایجاد', 'fa'),
("cancel", 'لغو', 'fa'),
("useractive", 'کاربر فعال است', 'fa'),

("createstudent.name", 'نام (نام، نام خانوادگی)', 'fa'),
("createstudent.studentid", 'شماره دانشجویی', 'fa'),
("createstudent.studentdegree", 'مدرک دانشجو', 'fa'),
("createstudent.password", 'رمز عبور', 'fa'),

("createcourse.coursename", 'نام دوره', 'fa'),
("createcourse.topic", 'موضوع دوره', 'fa'),
("createcourse.description", 'توضیحات دوره', 'fa'),
("createcourse.minattend", 'حداقل حضور', 'fa'),
("createcourse.maxattend", 'حداکثر حضور', 'fa'),
("createcourse.codeattend", 'کد حضور', 'fa'),
("createcourse.coursactive", 'دوره فعال است', 'fa'),
("createcourse.isattend", 'حضور الزامی است', 'fa'),
("createcourse.attendcode", 'کد حضور', 'fa'),

("createstaff.name", 'نام کارمند', 'fa'),
("createstaff.role", 'نقش کارمند', 'fa'),
("createstaff.password", 'رمز عبور', 'fa'),
("createstaff.isadmin", 'مدیر است', 'fa'),

("createdegree.name", 'نام مدرک', 'fa'),
("createdegree.ects", 'واحدهای ECTS', 'fa'),

("tableview.id", 'شناسه', 'fa'),
("tableview.studId", 'شناسه دانشجو', 'fa'),
("tableview.courId", 'شناسه دوره', 'fa'),
("tableview.staffId", 'شناسه کارمند', 'fa'),
("tableview.ects", 'واحدهای ECTS', 'fa'),
("tableview.name", 'نام', 'fa'),
("tableview.role", 'نقش', 'fa'),
("tableview.admin", 'مدیر است', 'fa'),
("tableview.degree", 'مدرک', 'fa'),
("tableview.topic", 'موضوع', 'fa'),
("tableview.desc", 'توضیحات', 'fa'),
("tableview.attenAv", 'حضور امکان‌پذیر', 'fa'),
("tableview.attenKey", 'رمز حضور', 'fa'),
("tableview.minAtt", 'حداقل حضور', 'fa'),
("tableview.maxAtt", 'حداکثر طول دوره', 'fa'),
("tableview.active", 'فعال', 'fa'),
("tableview.handl", 'رسیدگی شده', 'fa'),
("tableview.curr", 'مقدار', 'fa'),
("tableview.time", 'ایجاد شده', 'fa'),

("deletion.noselect", 'هیچ موردی انتخاب نشده است', 'fa'),
("deletion.errorfetch", 'خطا در هنگام دریافت شناسه ردیف. خطا:', 'fa'),
("deletion.confirmtitle", 'تأیید حذف', 'fa'),
("deletion.confirm", 'آیا مطمئن هستید که می‌خواهید این مورد را حذف کنید؟', 'fa'),

("modif.noselect", 'هیچ موردی انتخاب نشده است', 'fa'),
("modif.errorfetch", 'خطا در هنگام دریافت شناسه ردیف. خطا:', 'fa'),
("modif.errordial", 'خطا در هنگام باز کردن دیالوگ', 'fa'),
("modif.updateBtn", 'به‌روزرسانی', 'fa'),
("modif.password", 'رمز عبور را تغییر دهید. برای حفظ رمز فعلی، خالی بگذارید.', 'fa'),
("modif.title", 'ویرایش مورد', 'fa');
