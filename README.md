<div align="center">
  <h1> SISTEM PENYEWAAN HANDPHONE</h1>
  <p align="center">
  <img src="https://img.shields.io/badge/Language-Java-red?style=for-the-badge&logo=java" alt="Java" />
  <img src="https://img.shields.io/badge/Paradigm-OOP-green?style=for-the-badge" alt="OOP" />
  <img src="https://img.shields.io/badge/Aplikasi-Apache%20NetBeans-blue?style=for-the-badge" alt="Apache NetBeans" />

</div>

<hr />

<table>
  <tr>
    <td width="150"><b>Nama</b></td>
    <td>: Rifaa Zainul Arifin</td>
  </tr>
  <tr>
    <td><b>NIM</b></td>
    <td>: 2509116092</td>
  </tr>
    <td><b>KELAS</b></td>
    <td>: C</td>
  </tr>
</table>

## <b>1. Deskripsi Singkat Program</b>

Sistem Sewa Handphone merupakan program berbasis Java yang digunakan untuk mengelola data handphone, data pelanggan, serta proses penyewaan dan pengembalian handphone. Program dijalankan melalui console dan menggunakan database <b>MySQL</b> yang dihubungkan melalui <b>JDBC</b> dengan pola <b>DAO</b> sebagai tempat penyimpanan data.

Program dikembangkan menggunakan konsep <b>Object Oriented Programming (OOP)</b> dan menerapkan struktur <b>MVC (Model, View, Controller)</b> agar kode program lebih terstruktur dan setiap class memiliki fungsi yang jelas.

Program memiliki beberapa fitur utama, yaitu:

* <b>Kelola Handphone</b>, digunakan untuk menambah, melihat, mengubah, dan menghapus data handphone.
* <b>Kelola Pelanggan</b>, digunakan untuk menambah, melihat, mengubah, dan menghapus data pelanggan.
* <b>Penyewaan Handphone</b>, digunakan untuk membuat transaksi penyewaan harian atau mingguan.
* <b>Pengembalian Handphone</b>, digunakan untuk menyelesaikan transaksi penyewaan yang masih aktif.
* <b>Riwayat Penyewaan</b>, digunakan untuk melihat seluruh transaksi penyewaan yang pernah dilakukan.

Program juga menerapkan <b>validasi input, access modifier, encapsulation, inheritance, polymorphism, method overriding, ID otomatis, Interface, JDBC, Prepared Statement, DAO, dan dummy data dari database</b>.

---

## <b>2. Struktur Packages Program</b>

Program menerapkan struktur <b>MVC (Model, View, Controller)</b> dengan memisahkan class ke dalam beberapa package agar program lebih rapi dan setiap bagian memiliki tugas masing-masing.

<p align="center">
  <b>Gambar 1. Struktur Package Program</b>

<p align="left">
<img width="309" height="584" alt="Screenshot 2026-10-15 114556" src="https://github.com/user-attachments/assets/895255c9-c20b-4cbc-974f-1288ea28de17" />

</p>

### <b>A. Package Main</b>

Package <b>main</b> berisi class Main yang menjadi titik awal untuk menjalankan program.

Class Main membuat objek Controller dan View, kemudian menjalankan program melalui method jalankan() pada View.

### <b>B. Package Model</b>

Package <b>model</b> berisi class yang digunakan untuk membentuk dan menyimpan data utama program.

Class Handphone digunakan untuk menyimpan data handphone, sedangkan class Pelanggan digunakan untuk menyimpan data pelanggan. Class Sewa digunakan sebagai superclass penyewaan yang kemudian diturunkan menjadi SewaHarian dan SewaMingguan.

Pada bagian Model juga terdapat validasi terhadap atribut masing-masing objek, seperti validasi merk, tipe, harga sewa, nama pelanggan, nomor HP, NIK, status, dan durasi penyewaan.

### <b>C. Package Controller</b>

Package <b>controller</b> berisi class `Controller` yang digunakan untuk mengatur proses dan data program. Class `Controller` mengimplementasikan interface <b>CrudHandphone</b> dan <b>CrudPelanggan</b> sebagai kontrak untuk mengatur operasi CRUD pada data handphone dan pelanggan.

Interface <b>CrudHandphone</b> digunakan untuk menentukan method yang berkaitan dengan pengelolaan data handphone, seperti menambah, mencari, mengubah, menghapus, dan mengambil daftar handphone. Sedangkan interface <b>CrudPelanggan</b> digunakan untuk menentukan method pengelolaan data pelanggan, seperti menambah, mencari, mengubah, menghapus, dan mengambil daftar pelanggan.

Controller memanggil class <b>DAO</b> untuk membaca dan menyimpan data Handphone, Pelanggan, dan Sewa ke database. Controller juga menangani pemanggilan pembuatan ID otomatis, pencarian data, pengecekan data duplikat, proses penyewaan, pengembalian handphone, serta perubahan status data.

Dengan demikian, Controller menjadi penghubung antara data pada Model dengan tampilan dan input yang terdapat pada View, sekaligus menjadi tempat implementasi method yang telah ditentukan oleh interface `CrudHandphone` dan `CrudPelanggan`.

### <b>D. Package View</b>

Package <b>view</b> berisi class View yang digunakan untuk berinteraksi langsung dengan pengguna melalui console.

View bertugas menampilkan menu, menerima input pengguna, menampilkan data, memberikan pesan validasi, meminta konfirmasi, dan memanggil Controller ketika pengguna ingin melakukan suatu proses.

---

## <b>3. Penjelasan Alur Program</b>

Ketika program pertama kali dijalankan, class Main membuat objek Controller dan View. Data awal (<b>dummy data</b>) Handphone, Pelanggan, dan satu riwayat sewa sudah tersimpan di database melalui file `sewa_hp.sql` sehingga data dapat langsung ditampilkan tanpa harus melakukan input dari awal.

Setelah itu sistem menampilkan dashboard dan Menu Utama.

Menu Utama terdiri dari:

1. Kelola Handphone
2. Kelola Pelanggan
3. Penyewaan Handphone
4. Pengembalian Handphone
5. Riwayat Penyewaan
6. Keluar

Dashboard juga menampilkan informasi jumlah handphone, jumlah handphone tersedia, handphone yang sedang disewa, jumlah pelanggan, dan jumlah transaksi sewa aktif.

<p align="center">
  <b>Gambar 2. Menu Utama</b>

<p align="left">
<img width="500" height="500" alt="Screenshot 2026-10-06 191416" src="https://github.com/user-attachments/assets/a7a40396-d2c7-4b37-82d1-9a5041a13aad" />

</p>

---

### <b>3.1 Alur Kelola Handphone</b>

Menu Kelola Handphone digunakan untuk mengelola seluruh data handphone yang tersedia pada sistem.

Menu ini terdiri dari:

1. Tambah Handphone
2. Lihat Handphone
3. Ubah Handphone
4. Hapus Handphone
5. Kembali

<p align="center">
  <b>Gambar 3. Menu Kelola Handphone</b>

<p align="left">
<img width="350" height=450" alt="Screenshot 2026-10-06 191950" src="https://github.com/user-attachments/assets/456af0af-1118-4a42-b122-59b618c865c3" />

</p>

#### <b>A. Tambah Handphone</b>

Pada proses tambah handphone, pengguna memasukkan merk, tipe, dan harga sewa per hari. Kode HP tidak perlu dimasukkan secara manual karena dibuat secara otomatis oleh sistem dengan format seperti HP001, HP002, dan seterusnya.

Input akan diperiksa terlebih dahulu melalui validasi pada class Handphone. Setelah seluruh data valid, sistem meminta konfirmasi sebelum data disimpan ke database. Dan juga ketika menginput merek ataupun tipe menggunakan angka dia akan gagal.

<p align="center">
  <b>Gambar 4. Proses Tambah Handphone</b>
<p align="left">
<img width="340" height="365" alt="Screenshot 2026-10-06 192321" src="https://github.com/user-attachments/assets/4a3801ed-fef1-4b83-83f6-23c46a27ee04" />

</p>

#### <b>B. Lihat Handphone</b>

Menu Lihat Handphone menampilkan seluruh data handphone yang tersimpan di dalam sistem. Informasi yang ditampilkan meliputi kode HP, merk, tipe, harga sewa per hari, dan status handphone.

Status awal handphone adalah <b>TERSEDIA</b>.

<p align="center">
  <b>Gambar 5. Daftar Handphone</b>
<p align="left">
<img width="566" height="126" alt="image" src="https://github.com/user-attachments/assets/f69d537f-3037-42a6-a87b-8b3fec85978b" />


</p>

#### <b>C. Ubah Handphone</b>

Pada proses ubah data, pengguna memilih handphone berdasarkan kode HP. Sistem akan mencari handphone tersebut kemudian menampilkan data saat ini.

Pengguna dapat mengubah merk, tipe, dan harga sewa. Handphone yang sedang berstatus <b>DISEWA</b> tidak dapat diubah sampai handphone tersebut dikembalikan.

<p align="center">
  <b>Gambar 6. Proses Ubah Handphone</b>
<p align="left">
<img width="590" height="325" alt="image" src="https://github.com/user-attachments/assets/84459f16-fcc0-44a3-ae6b-64bdfb932340" />


</p>

#### <b>D. Hapus Handphone</b>

Pengguna memilih handphone yang ingin dihapus berdasarkan kode HP. Sebelum penghapusan dilakukan, sistem akan meminta konfirmasi.

Handphone yang sudah mempunyai riwayat penyewaan tidak dapat dihapus karena data tersebut masih digunakan dalam riwayat transaksi.

<p align="center">
  <b>Gambar 7. Proses Hapus Handphone</b>
<p align="left">
<img width="591" height="206" alt="image" src="https://github.com/user-attachments/assets/cb35774f-a4d4-47f1-95b0-fdc6f72afee5" />


</p>

---

### <b>3.2 Alur Kelola Pelanggan</b>

Menu Kelola Pelanggan digunakan untuk mengelola data pelanggan yang menggunakan layanan penyewaan handphone.

Menu ini terdiri dari:

1. Tambah Pelanggan
2. Lihat Pelanggan
3. Ubah Pelanggan
4. Hapus Pelanggan
5. Kembali

<p align="center">
  <b>Gambar 8. Menu Kelola Pelanggan</b>
<p align="left">
<img width="346" height="360" alt="Screenshot 2026-10-07 143259" src="https://github.com/user-attachments/assets/a287e6de-4913-454d-826c-c744ead46689" />

</p>

#### <b>A. Tambah Pelanggan</b>

Pengguna memasukkan nama, nomor HP, dan NIK pelanggan. ID pelanggan tidak dimasukkan secara manual karena dibuat otomatis oleh sistem dengan format P001, P002, dan seterusnya.

Sistem melakukan validasi nama, nomor HP, dan NIK. Nomor HP harus diawali '08', sedangkan NIK harus terdiri dari 16 digit. Sistem juga mencegah nomor HP dan NIK yang sama digunakan oleh pelanggan lain.

<p align="center">
  <b>Gambar 9. Proses Tambah Pelanggan</b>
<p align="left">
<img width="331" height="275" alt="Screenshot 2026-10-07 143915" src="https://github.com/user-attachments/assets/0546f3bf-b3ee-4f77-ba0d-e502d04ab522" />

</p>

#### <b>B. Lihat Pelanggan</b>

Menu Lihat Pelanggan menampilkan seluruh pelanggan yang telah tersimpan di database.

Informasi yang ditampilkan meliputi ID pelanggan, nama, nomor HP, dan NIK.

<p align="center">
  <b>Gambar 10. Daftar Pelanggan</b>
<p align="left">
<img width="533" height="268" alt="Screenshot 2026-10-07 144725" src="https://github.com/user-attachments/assets/10741d8f-9215-4064-83e7-99af0c7c8bbc" />

</p>

#### <b>C. Ubah Pelanggan</b>

Pengguna memilih pelanggan berdasarkan ID. Setelah pelanggan ditemukan, pengguna dapat mengubah nama, nomor HP, dan NIK.

Data baru tetap melalui proses validasi dan sistem memastikan nomor HP maupun NIK tidak digunakan oleh pelanggan lainnya.

<p align="center">
  <b>Gambar 11. Proses Ubah Pelanggan</b>
<p align="left">
<img width="539" height="320" alt="Screenshot 2026-10-07 144909" src="https://github.com/user-attachments/assets/1a8e72be-b046-43b8-9e5d-ce69b7fdae02" />

</p>

#### <b>D. Hapus Pelanggan</b>

Pengguna memilih pelanggan berdasarkan ID kemudian sistem meminta konfirmasi sebelum penghapusan.

Pelanggan yang sudah memiliki riwayat penyewaan tidak dapat dihapus agar data pada riwayat transaksi tetap terjaga.

<p align="center">
  <b>Gambar 12. Proses Hapus Pelanggan</b>
<p align="left">
<img width="538" height="190" alt="Screenshot 2026-10-07 145003" src="https://github.com/user-attachments/assets/6a1809ba-59e2-480c-acd7-fc7cc6d909f8" />

</p>

---

### <b>3.3 Alur Penyewaan Handphone</b>

Menu Penyewaan Handphone digunakan untuk membuat transaksi penyewaan baru.

Pada proses ini, sistem terlebih dahulu menampilkan daftar pelanggan. Pengguna memilih pelanggan menggunakan <b>ID Pelanggan</b>.

Setelah pelanggan dipilih, sistem menampilkan handphone yang berstatus <b>TERSEDIA</b>. Pengguna kemudian memilih handphone berdasarkan kode HP.

Selanjutnya pengguna memilih jenis penyewaan:

* <b>Sewa Harian</b>, dengan durasi 1 sampai 6 hari.
* <b>Sewa Mingguan</b>, dengan durasi 1 sampai 4 minggu dan mendapatkan diskon 10%.

Setelah jenis dan durasi dipilih, sistem menghitung total biaya penyewaan secara otomatis dan menampilkan detail transaksi untuk dikonfirmasi.

Jika penyewaan dikonfirmasi, sistem membuat ID sewa secara otomatis dengan format seperti 'SW001', menyimpan transaksi ke tabel `sewa` di database, dan mengubah status handphone dari <b>TERSEDIA</b> menjadi <b>DISEWA</b>.

<p align="center">
  <b>Gambar 13. Konfirmasi Penyewaan Handphone</b>
<p align="left">
<img width="401" height="183" alt="Screenshot 2026-10-07 162934" src="https://github.com/user-attachments/assets/c8bdffa0-061f-494b-8085-f49c356065dc" />

</p>

<p align="center">
  <b>Gambar 14. Penyewaan Handphone Berhasil</b>
<p align="left">
<img width="360" height="117" alt="Screenshot 2026-10-07 162938" src="https://github.com/user-attachments/assets/b4f7a35a-c956-4ea1-9106-39799afe74ec" />

</p>

---

### <b>3.4 Alur Pengembalian Handphone</b>

Menu Pengembalian Handphone digunakan untuk menyelesaikan transaksi yang masih berstatus <b>AKTIF</b>.

Sistem terlebih dahulu menampilkan daftar penyewaan aktif. Pengguna memilih transaksi berdasarkan ID Sewa.

Setelah transaksi ditemukan, sistem menampilkan detail penyewaan yang terdiri dari ID Sewa, pelanggan, handphone, jenis sewa, durasi, dan total biaya.

Setelah pengguna melakukan konfirmasi pengembalian, status transaksi berubah dari <b>AKTIF</b> menjadi <b>SELESAI</b> dan status handphone berubah dari <b>DISEWA</b> kembali menjadi <b>TERSEDIA</b>.

Dengan demikian, handphone tersebut dapat digunakan kembali untuk transaksi penyewaan berikutnya.

<p align="center">
  <b>Gambar 15. Konfirmasi Pengembalian Handphone</b>
<p align="left">
<img width="421" height="495" alt="Screenshot 2026-10-07 163149" src="https://github.com/user-attachments/assets/b7626138-c90c-4b91-b598-ef1549793c92" />

</p>

<p align="center">
  <b>Gambar 16. Pengembalian Handphone Berhasil</b>
<p align="left">
<img width="359" height="91" alt="image" src="https://github.com/user-attachments/assets/f99ee3b6-59cc-44dc-934d-f04b080a2718" />


</p>

---

### <b>3.5 Alur Riwayat Penyewaan</b>

Menu Riwayat Penyewaan digunakan untuk melihat seluruh transaksi penyewaan yang pernah dilakukan.

Informasi yang ditampilkan terdiri dari ID Sewa, pelanggan, handphone, jenis penyewaan, durasi, total biaya, dan status transaksi.

Transaksi yang belum dikembalikan memiliki status <b>AKTIF</b>, sedangkan transaksi yang sudah melalui proses pengembalian memiliki status <b>SELESAI</b>.

Riwayat transaksi tetap disimpan meskipun handphone sudah dikembalikan.

<p align="center">
  <b>Gambar 17. Riwayat Penyewaan</b>
<p align="left">
<img width="449" height="245" alt="image" src="https://github.com/user-attachments/assets/c89b8a36-3283-4ce4-9db4-80a24dfa7f24" />


</p>

---

### <b>3.6 Keluar Program</b>

Ketika pengguna memilih menu '0. Keluar', maka sistem akan menghentikan perulangan Menu Utama dan menampilkan pesan:

```
Terima kasih.
Program selesai.
```

Setelah itu program selesai dijalankan.

---

## <b>4. Penerapan Encapsulation</b>

Program menerapkan <b>encapsulation</b> dengan membuat atribut pada setiap class menggunakan access modifier 'private'.

Contohnya pada class 'Pelanggan', atribut ID pelanggan, nama, nomor HP, dan NIK tidak dapat diakses secara langsung dari class lain.

Akses terhadap data dilakukan menggunakan method <b>getter</b>, sedangkan perubahan data dilakukan melalui <b>setter</b>.

Setter juga dilengkapi dengan validasi sehingga data yang dimasukkan harus sesuai dengan aturan yang telah ditentukan.

Beberapa contoh aturan validasi yang diterapkan adalah:

* Nama pelanggan harus terdiri dari 3-50 karakter dan hanya berisi huruf serta spasi.
* Nomor HP harus diawali '08' dan terdiri dari 10-15 digit.
* NIK harus terdiri dari tepat 16 digit angka.
* Merk handphone harus terdiri dari 2-30 karakter.
* Tipe handphone harus terdiri dari 2-50 karakter.
* Harga sewa harus berada antara Rp1.000 sampai Rp10.000.000.
* Status handphone hanya dapat berupa 'TERSEDIA' atau 'DISEWA'.

Atribut seperti 'kodeHP' dan 'idPelanggan' juga menggunakan 'final' karena ID tersebut tidak boleh berubah setelah objek dibuat.

<p align="center">
  <b>Gambar 18. Penerapan Encapsulation pada Pelanggan</b>
<p align="left">
!<img width="1180" height="907" alt="image" src="https://github.com/user-attachments/assets/9f191c24-870b-410c-b849-2ee4850c2d77" />
<p align="left">
<img width="1180" height="907" alt="image" src="https://github.com/user-attachments/assets/94a176e0-eb64-4569-bb66-b411aae3fdfd" />


</p>

---

## <b>5. Penerapan Inheritance</b>

Program menerapkan <b>inheritance</b> pada proses penyewaan.

Class 'Sewa' digunakan sebagai <b>superclass</b> dan memiliki dua subclass, yaitu:

* 'SewaHarian'
* 'SewaMingguan'

Class 'SewaHarian' dan 'SewaMingguan' menggunakan keyword 'extends Sewa', sehingga keduanya mewarisi atribut dan method yang terdapat pada superclass Sewa.

Data yang diwariskan antara lain:

* ID Sewa
* Pelanggan
* Handphone
* Status
* Total biaya

Walaupun memiliki data dasar yang sama, kedua jenis penyewaan memiliki cara perhitungan biaya dan durasi yang berbeda.

Pada 'SewaHarian', total biaya dihitung berdasarkan:

```
Harga sewa per hari × jumlah hari
```

Sedangkan pada 'SewaMingguan', total biaya dihitung berdasarkan jumlah minggu dan mendapatkan diskon sebesar 10%.

<p align="center">
  <b>Gambar 19. Penerapan Inheritance pada Class Sewa</b>
<p align="left">
<img width="415" height="57" alt="Screenshot 2026-10-07 163656" src="https://github.com/user-attachments/assets/84cf8610-1912-46d2-9c7f-3640997b67c4" />
<img width="602" height="79" alt="image" src="https://github.com/user-attachments/assets/532a4299-3a12-4846-a2b6-6604313fc7b4" />


</p>

---

## <b>6. Penerapan Polymorphism</b>

Program juga menerapkan <b>polymorphism</b> dan <b>method overriding</b> sebagai nilai tambah.

Pada superclass 'Sewa' terdapat beberapa abstract method, yaitu:

* hitungTotal()
* getJenisSewa()
* getDurasi()

Method tersebut kemudian di-override pada class 'SewaHarian' dan 'SewaMingguan' menggunakan '@Override'.

Pada 'SewaHarian', method 'hitungTotal()' menghitung biaya berdasarkan jumlah hari. Sedangkan pada 'SewaMingguan', method yang sama menghitung biaya berdasarkan jumlah minggu dan diskon 10%.

<p align="center">
  <b>Gambar 20. Penerapan Polymorphism dan Method Overriding</b>
<p align="left">
<img width="604" height="419" alt="image" src="https://github.com/user-attachments/assets/ee5cc081-fff6-469a-b53e-3e2ed958e325" />


</p>

---

## <b>7. Penerapan Validasi Input</b>

Program menerapkan validasi input agar data yang dimasukkan pengguna sesuai dengan aturan sistem dan mengurangi kemungkinan kesalahan input.

Validasi ditempatkan pada class yang sesuai dengan data yang dimiliki.

Pada class 'Handphone' terdapat validasi:

* Merk handphone.
* Tipe handphone.
* Harga sewa.
* Status handphone.

Pada class 'Pelanggan' terdapat validasi:

* Nama pelanggan.
* Nomor HP.
* NIK.

Pada class 'SewaHarian' terdapat validasi jumlah hari penyewaan antara 1 sampai 6 hari.

Pada class 'SewaMingguan' terdapat validasi jumlah minggu penyewaan antara 1 sampai 4 minggu.

Controller juga melakukan pengecekan terhadap hubungan antar data, seperti memastikan nomor HP dan NIK tidak digunakan oleh pelanggan lain, memastikan handphone tersedia sebelum disewa, serta mencegah penghapusan data yang sudah memiliki riwayat penyewaan.

View akan menampilkan pesan apabila input tidak sesuai dan meminta pengguna memasukkan data kembali.

<p align="center">
  <b>Gambar 21. Implementasi Validasi Input</b>
<p align="left">
<img width="404" height="401" alt="validasi" src="https://github.com/user-attachments/assets/41880b75-0b93-4cf6-87b5-8e8ba07eca73" />

</p>

---

## <b>8. Penjelasan MVC dan Polymorphism</b>

### <b>8.1 Struktur MVC</b>

Program menggunakan struktur <b>MVC (Model, View, Controller)</b> agar kode lebih terstruktur.

<b>Model</b> bertanggung jawab terhadap data dan aturan dari masing-masing objek.

<b>View</b> bertanggung jawab terhadap tampilan menu, crud, menerima input pengguna, menampilkan output, dan memberikan pesan kepada pengguna.

<b>Controller</b> bertanggung jawab mengatur proses program dan menjadi penghubung antara View dengan Model.

<b>Main</b> merupakan program utama dijalankan.

Penerapan MVC membuat setiap class memiliki tanggung jawab yang lebih jelas sehingga program lebih mudah dipahami dan dikembangkan.

---


## <b>9. Integrasi Database dengan JDBC dan DAO</b>

Pada tahap ini, penyimpanan data tidak lagi memakai `ArrayList` di dalam program, tetapi dipindahkan ke database <b>MySQL</b>. Program terhubung ke database menggunakan <b>JDBC</b> dengan <b>Prepared Statement</b>, dan seluruh akses database dipisahkan ke dalam class <b>DAO (Data Access Object)</b>.

Dengan perubahan ini, data tetap tersimpan walaupun program dihentikan dan dijalankan kembali.

### <b>9.1 Alat yang Digunakan</b>

| Komponen | Keterangan |
|---|---|
| Database | Laragon |
| Pengelola database | HeidiSQL (bawaan Laragon) |
| Driver | MySQL Connector/J 8.4.0, ditambahkan lewat `pom.xml` (Maven) |
| IDE | Apache NetBeans |

Driver ditambahkan pada `pom.xml` sehingga Maven mengunduhnya otomatis:

```xml
<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.4.0</version>
    </dependency>
</dependencies>
```

### <b>9.2 Struktur Package Setelah Integrasi</b>

Struktur MVC tetap dipertahankan. Ditambahkan dua package baru, yaitu `dao` dan `database`, serta satu file SQL.

```
src/main/java/
├── main/         Main
├── model/        Handphone, Pelanggan, Sewa, SewaHarian, SewaMingguan
├── view/         View
├── controller/   Controller, CrudHandphone, CrudPelanggan
├── dao/          HandphoneDAO, PelangganDAO, SewaDAO        (BARU)
└── database/     Koneksi                                    (BARU)
database/
└── sewa_hp.sql   skema tabel + dummy data                   (BARU)
```

<p align="center">
  <b>Gambar 26. Struktur Package Setelah Integrasi Database</b>
<p align="left">

<!-- TEMPEL SCREENSHOT struktur package di panel Projects NetBeans -->

</p>

Alur pemanggilan data sekarang:

```
View → Controller → DAO → Koneksi → MySQL
```

* <b>View</b> hanya menampilkan menu dan menerima input.
* <b>Controller</b> melakukan validasi dan mengatur proses, lalu memanggil DAO.
* <b>DAO</b> berisi seluruh perintah SQL.
* <b>Koneksi</b> menyediakan koneksi ke database.

### <b>9.3 Struktur Database</b>

Database bernama `sewa_hp` dan terdiri dari tiga tabel.

<b>Tabel `handphone`</b>

| Kolom | Tipe | Keterangan |
|---|---|---|
| kode_hp | VARCHAR(10) | Primary key, contoh HP001 |
| merk | VARCHAR(30) | Merk handphone |
| tipe | VARCHAR(50) | Tipe handphone |
| harga_sewa | DECIMAL(12,2) | Harga sewa per hari |
| status | VARCHAR(10) | TERSEDIA atau DISEWA |

<b>Tabel `pelanggan`</b>

| Kolom | Tipe | Keterangan |
|---|---|---|
| id_pelanggan | VARCHAR(10) | Primary key, contoh P001 |
| nama | VARCHAR(50) | Nama pelanggan |
| no_hp | VARCHAR(15) | Unique |
| nik | CHAR(16) | Unique |

<b>Tabel `sewa`</b>

| Kolom | Tipe | Keterangan |
|---|---|---|
| id_sewa | VARCHAR(10) | Primary key, contoh SW001 |
| id_pelanggan | VARCHAR(10) | Foreign key ke `pelanggan` |
| kode_hp | VARCHAR(10) | Foreign key ke `handphone` |
| jenis | VARCHAR(10) | HARIAN atau MINGGUAN |
| durasi | INT | Jumlah hari atau jumlah minggu |
| total_biaya | DECIMAL(14,2) | Total biaya saat transaksi dibuat |
| status | VARCHAR(10) | AKTIF atau SELESAI |

Relasi antar tabel:

```mermaid
erDiagram
    PELANGGAN ||--o{ SEWA : melakukan
    HANDPHONE ||--o{ SEWA : disewa
    PELANGGAN {
        varchar id_pelanggan PK
        varchar nama
        varchar no_hp
        char nik
    }
    HANDPHONE {
        varchar kode_hp PK
        varchar merk
        varchar tipe
        decimal harga_sewa
        varchar status
    }
    SEWA {
        varchar id_sewa PK
        varchar id_pelanggan FK
        varchar kode_hp FK
        varchar jenis
        int durasi
        decimal total_biaya
        varchar status
    }
```

Foreign key membuat database ikut menjaga aturan program: handphone atau pelanggan yang sudah punya riwayat sewa tidak dapat dihapus.

<p align="center">
  <b>Gambar 27. Tabel pada Database sewa_hp</b>
<p align="left">

<!-- TEMPEL SCREENSHOT daftar 3 tabel di HeidiSQL -->

</p>

### <b>9.4 Dummy Data dari File SQL</b>

Data awal <b>tidak diisi lewat kode program</b>. Method `isiDataAwal()` pada `Controller` sudah dihapus. Seluruh data awal berada di file `database/sewa_hp.sql` pada bagian `INSERT`:

```sql
INSERT INTO handphone (kode_hp, merk, tipe, harga_sewa, status) VALUES
('HP001', 'Samsung', 'Galaxy S23',    100000, 'TERSEDIA'),
('HP002', 'Apple',   'iPhone 14',     150000, 'TERSEDIA'),
('HP003', 'Xiaomi',  'Redmi Note 13',  75000, 'TERSEDIA');

INSERT INTO pelanggan (id_pelanggan, nama, no_hp, nik) VALUES
('P001', 'Pompom',     '081234567890', '6472010101010001'),
('P002', 'Yahya SOTM', '082345678901', '6472010202020002');

INSERT INTO sewa (id_sewa, id_pelanggan, kode_hp, jenis, durasi, total_biaya, status) VALUES
('SW001', 'P001', 'HP001', 'HARIAN', 3, 300000, 'SELESAI');
```

Dengan data ini, saat fitur <b>Lihat Handphone</b>, <b>Lihat Pelanggan</b>, dan <b>Riwayat Penyewaan</b> dijalankan, data langsung tampil tanpa input dari awal.

Satu riwayat sewa dibuat berstatus `SELESAI` sehingga ketiga handphone tetap berstatus `TERSEDIA` dan siap dipakai untuk uji penyewaan.

<p align="center">
  <b>Gambar 28. Dummy Data pada Tabel Database</b>
<p align="left">

<img width="858" height="136" alt="Screenshot 2026-10-12 200843" src="https://github.com/user-attachments/assets/227b39f0-e54b-4ed8-81c8-06f6eee32e7b" /><img width="894" height="211" alt="Screenshot 2026-10-12 200835" src="https://github.com/user-attachments/assets/0aae8b42-613f-4ab4-bfd8-6136a2168dfd" />
<img width="932" height="93" alt="Screenshot 2026-10-12 200901" src="https://github.com/user-attachments/assets/8e627cd4-aac9-43d3-95d0-c23bfe713ca1" />
>

</p>

### <b>9.5 Class Koneksi</b>

Package <b>database</b> berisi class `Koneksi` yang menyediakan koneksi ke MySQL.

```java
public class Koneksi {
    private static final String URL  = "jdbc:mysql://localhost:3306/db_sewa_hp";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
```

Setiap method DAO membuka koneksi lewat <b>try-with-resources</b>, sehingga `Connection`, `PreparedStatement`, dan `ResultSet` otomatis ditutup setelah selesai dipakai.

<p align="center">
  <b>Gambar 29. Class Koneksi</b>
<p align="left">

<!-- TEMPEL SCREENSHOT Koneksi.java -->

</p>

### <b>9.6 Penerapan DAO</b>

Package <b>dao</b> berisi tiga class, satu untuk setiap tabel:

| Class DAO | Tugas |
|---|---|
| `HandphoneDAO` | insert, findByKode, findAll, update, delete, generateKode |
| `PelangganDAO` | insert, findById, findAll, update, delete, cek NIK dan No HP duplikat, generateId |
| `SewaDAO` | insert sewa, selesai (pengembalian), findById, findAll, cek riwayat, generateId |

Pemisahan ini membuat `Controller` tidak lagi berisi perintah SQL. Controller cukup memanggil method DAO, misalnya:

```java
private final HandphoneDAO hpDAO = new HandphoneDAO();

@Override
public Handphone[] getDaftarHandphone() {
    return hpDAO.findAll();
}
```

Karena nama method pada Controller dan interface `CrudHandphone` serta `CrudPelanggan` tetap sama, tampilan pada `View` hampir tidak berubah. Perubahan di View hanya penyesuaian dari `.size()` ke `.length` dan dari `.isEmpty()` ke `.length == 0`, karena daftar data sekarang dikembalikan dalam bentuk array.

<p align="center">
  <b>Gambar 30. Class HandphoneDAO</b>
<p align="left">

<!-- TEMPEL SCREENSHOT HandphoneDAO.java -->

</p>
