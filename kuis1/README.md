# Student Manager

Aplikasi Android untuk mengelola data mahasiswa (NIM, nama, dan program studi), dibuat sebagai tugas kuis mata kuliah Pemrograman Perangkat Bergerak.

## Studi Kasus

Selama ini pendataan mahasiswa di lingkungan kampus, khususnya untuk keperluan kelas kecil, organisasi mahasiswa, atau praktikum, masih sering dilakukan secara manual lewat catatan kertas atau spreadsheet yang terpisah dari konteks penggunaannya. Cara ini rawan data hilang, sulit dicari kembali kalau jumlah mahasiswa sudah banyak, dan tidak praktis diakses saat dibutuhkan secara cepat, misalnya saat presensi atau pengecekan jurusan mahasiswa di lapangan.

Dari masalah itu, Student Manager dibangun sebagai aplikasi mobile ringan yang memungkinkan pengguna mencatat, mencari, mengubah, dan menghapus data mahasiswa langsung dari perangkat Android, tanpa perlu koneksi internet atau aplikasi tambahan.

## Fitur

- **Splash Screen**: menampilkan logo dan nama aplikasi selama 2 detik sebelum masuk ke halaman utama.
- **Daftar Mahasiswa**: menampilkan seluruh data mahasiswa dalam bentuk kartu (nama, NIM, program studi) beserta jumlah total mahasiswa yang tercatat.
- **Pencarian Real-time**: mencari mahasiswa berdasarkan nama, NIM, atau program studi, dengan tombol clear untuk menghapus kata kunci pencarian.
- **Tambah Mahasiswa**: form input NIM, nama, dan program studi (dipilih lewat dropdown) untuk menambahkan data baru.
- **Edit Mahasiswa**: form yang sama dengan tambah data, tapi field-nya sudah terisi otomatis sesuai data yang dipilih.
- **Hapus Mahasiswa**: dilengkapi dialog konfirmasi supaya data tidak terhapus tanpa sengaja.
- **Empty State**: tampilan khusus saat belum ada data sama sekali, mengarahkan pengguna untuk menambahkan data pertama.
- **Menu Tambahan**: opsi Refresh, Tentang Aplikasi, dan Keluar lewat overflow menu di TopAppBar.

Sebagai data awal, aplikasi sudah diisi 3 mahasiswa contoh (Budi Santoso, Siti Aminah, Andi Wijaya) supaya tampilan langsung terisi begitu dijalankan.

## Tech Stack

| Komponen | Teknologi |
|---|---|
| Bahasa | Kotlin |
| UI | Jetpack Compose |
| Design System | Material Design 3 |
| Navigasi | Navigation Compose |
| Arsitektur | MVVM + StateFlow |

## Struktur Project

```
app/src/main/java/com/example/studentmanager/
├── data/
│   ├── Student.kt              # data class Student + daftar program studi
│   └── StudentViewModel.kt     # state & logic CRUD + pencarian
├── ui/
│   ├── navigation/NavGraph.kt  # daftar route navigasi
│   ├── screens/
│   │   ├── splash/SplashScreen.kt
│   │   ├── studentlist/StudentListScreen.kt
│   │   └── studentform/StudentFormScreen.kt
│   └── theme/                  # warna & tipografi Material 3
└── MainActivity.kt             # entry point + NavHost
```

## Alur Navigasi

```
splash -> (2 detik) -> student_list
student_list -> (FAB +) -> add_student -> (Simpan/Batal) -> student_list
student_list -> (ikon edit) -> edit_student/{id} -> (Simpan/Batal) -> student_list
```

## Cara Menjalankan

1. Buka folder project ini di Android Studio, biarkan Gradle sync selesai.
2. Jalankan lewat tombol Run atau `./gradlew installDebug` ke perangkat/emulator yang terhubung.

## Catatan

Data mahasiswa saat ini disimpan sementara di memori (`StateFlow` di ViewModel), jadi akan kembali ke data awal setiap kali aplikasi ditutup dan dibuka ulang. Ini sudah sesuai lingkup tugas yang fokus ke tampilan dan alur CRUD, bukan penyimpanan permanen.
