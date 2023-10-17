USE [master]
GO
/****** Object:  Database [DATN_ShopHoaDaisy_V2]    Script Date: 10/17/2023 4:12:32 PM ******/
CREATE DATABASE [DATN_ShopHoaDaisy_V2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DATN_ShopHoaDaisy_V2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DATN_ShopHoaDaisy_V2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DATN_ShopHoaDaisy_V2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DATN_ShopHoaDaisy_V2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DATN_ShopHoaDaisy_V2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET ARITHABORT OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET RECOVERY FULL 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET  MULTI_USER 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'DATN_ShopHoaDaisy_V2', N'ON'
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET QUERY_STORE = ON
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [DATN_ShopHoaDaisy_V2]
GO
/****** Object:  Table [dbo].[_order]    Script Date: 10/17/2023 4:12:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[_order](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[customer_id] [int] NOT NULL,
	[order_date] [datetime] NOT NULL,
	[pick_up_date] [datetime] NULL,
	[status] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[account]    Script Date: 10/17/2023 4:12:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[email] [varchar](100) NOT NULL,
	[password] [varchar](15) NOT NULL,
	[role_id] [int] NOT NULL,
	[active] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 10/17/2023 4:12:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 10/17/2023 4:12:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[customer_id] [int] IDENTITY(1,1) NOT NULL,
	[full_name] [nvarchar](255) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[phone_number] [varchar](255) NOT NULL,
	[gender] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 10/17/2023 4:12:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[order_details_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_details_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 10/17/2023 4:12:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[category_id] [int] NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[image] [nvarchar](255) NOT NULL,
	[price] [decimal](10, 2) NOT NULL,
	[quantity] [int] NOT NULL,
	[is_available] [bit] NOT NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 10/17/2023 4:12:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[role_id] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'caohoangkhang@gmail.com', N'123', 2, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'huynhvanni@gmail.com', N'123', 2, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'lecongthien@gmail.com', N'123', 2, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'lehoangtho@gmail.com', N'123', 2, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'lenguyennhutthien@gmail.com', N'123', 1, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'luuquochau@gmail.com', N'123', 2, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'nguyenphuocthien@gmail.com', N'123', 2, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'nguyenphutrong@gmail.com', N'123', 1, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'nguyenthingoccham@gmail.com', N'123', 1, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'tovulinh@gmail.com', N'123', 2, 1)
INSERT [dbo].[account] ([email], [password], [role_id], [active]) VALUES (N'vominhtam@gmail.com', N'123', 1, 1)
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([category_id], [name]) VALUES (1, N'Hoa bó')
INSERT [dbo].[category] ([category_id], [name]) VALUES (2, N'Hoa để bàn, giỏ hoa')
INSERT [dbo].[category] ([category_id], [name]) VALUES (3, N'Hoa khai trương')
INSERT [dbo].[category] ([category_id], [name]) VALUES (4, N'khác')
SET IDENTITY_INSERT [dbo].[category] OFF
GO
SET IDENTITY_INSERT [dbo].[customer] ON 

INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (1, N'Võ Minh Tâm', N'vominhtam@gmail.com', N'0123456789', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (2, N'lê Nguyễn Nhựt Thiên', N'lenguyennhutthien@gmail.com', N'0987654321', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (3, N'Nguyễn Phú Trọng', N'nguyenphutrong@gmail.com', N'0787684321', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (4, N'Nguyễn Thị Ngọc Chăm', N'nguyenthingoccham@gmail.com', N'0985654521', 1)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (5, N'Lê Công Thiện', N'lecongthien@gmail.com', N'0945654521', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (6, N'Lưu Quốc Hậu', N'luuquochau@gmail.com', N'0985665521', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (7, N'Cao Hoàng Khang', N'caohoangkhang@gmail.com', N'09854564521', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (8, N'Tô Vũ Linh', N'tovulinh@gmail.com', N'0985654621', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (9, N'Huỳnh Văn Ni', N'huynhvanni@gmail.com', N'0985674521', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (10, N'Nguyễn Phước Thiện', N'nguyenphuocthien@gmail.com', N'0985554521', 0)
INSERT [dbo].[customer] ([customer_id], [full_name], [email], [phone_number], [gender]) VALUES (11, N'Lê Hoàng Thọ', N'lehoangtho@gmail.com', N'0985656521', 0)
SET IDENTITY_INSERT [dbo].[customer] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (1, 1, N'Sen Tinh Khiết', N'SenTinhKhiet.jpg', CAST(400000.00 AS Decimal(10, 2)), 15, 1, N'Sen Trăng là sự hòa quyện giữa vẻ đẹp tự nhiên và ý nghĩa tâm linh chúng đại diện cho sự thuần khiết, 
thanh lịch và tinh tế. Do đó chúng sẽ là sự lựa chọn tuyệt vời để trao tăng cho những người chúng ta yêu thương.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (2, 1, N'Sắc Màu Tình Yêu', N'SacMauTinhYeu.jpg', CAST(300000.00 AS Decimal(10, 2)), 14, 1, N'Bó hoa hồng đỏ thường biểu hiện sự đam mê mãnh liệt, lòng trung hiếu, và cam kết vĩnh cửu trong tình yêu. "Sắc Màu Tình Yêu" không chỉ là về vẻ đẹp mắt của hoa, mà còn là về sự mãnh liệt và cháy bỏng của tình cảm.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (3, 1, N'Hồng Ngọt Ngào', N'BoHoaHongNgotNgao.jpg', CAST(500000.00 AS Decimal(10, 2)), 10, 1, N'Hoa hồng thường biểu hiện cho tình yêu và sự đam mê. Một bó hồng ngọt ngào khi trao đến tay những người thân yêu chúng thể hiện được một  tình cảm ngọt ngào, chân thành và dịu dàng.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (7, 1, N'Bó Hồng Ngây Thơ', N'BoHongTrangNgayTho.jpg', CAST(650000.00 AS Decimal(10, 2)), 1, 1, N'Khi bạn chọn tặng bó hoa hồng trắng, bạn không chỉ đơn giản là tặng một món quà đẹp mắt, mà còn truyền đạt một thông điệp sâu sắc về tình yêu, lòng chân thành và sự ngây thơ.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (8, 1, N'Hương Thơm Hồng Ngoại', N'HuongThomHongNgoai.webp', CAST(1000000.00 AS Decimal(10, 2)), 4, 1, N'Khi bạn tặng bó hoa hồng ngoại với sự tăng cường của quyến rũ và thuần khiết, bạn không chỉ truyền đạt vẻ đẹp ngoại hình mà còn truyền đạt những giá trị tinh tế và tâm hồn sâu sắc. Điều này làm cho món quà trở nên đặc biệt và đầy ý nghĩa trong tình yêu và sự kính trọng')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (10, 2, N'Vườn Hạnh Phúc', N'VuonHanhPhuc.jpg', CAST(650000.00 AS Decimal(10, 2)), 2, 1, N'Vườn hạnh phúc một hình ảnh với không gian ngập tràng hạnh phúc. Với một giỏ hoa đầy ấp hoa hồng vàng nó đại diện cho niền hạnh phúc và niềm vui không ngừng')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (12, 2, N'Nắng Vàng Bình Yên', N'VuonNangBinhYen.jpg', CAST(900000.00 AS Decimal(10, 2)), 4, 1, N'Lẵng hoa bao gồm hoa hướng dương kết hợp cùng hoa hồng kem vàng và những cành mõm sói vàng vươn cao làm điểm nhấn. Giỏ hoa chúc mừng khai trương,  kỷ niệm người thân, khách hàng, đối tác đều vô cùng ý nghĩa!')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (17, 2, N'Hoa Hồng Thanh Tao', N'HoaHongThanhTao.webp', CAST(700000.00 AS Decimal(10, 2)), 8, 1, N'Sản phẩm đã chứa đựng sự thanh tao và tinh khiết của hoa hồng, được kết hợp với các yếu tố thiên nhiên và ý nghĩa của lá táo và khuynh diệp. Sự kết hợp này tạo ra một hình ảnh tinh tế và đẳng cấp.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (19, 2, N'Bình Yên Nắng Hồng', N'BinhYenNangHong.jpg', CAST(950000.00 AS Decimal(10, 2)), 1, 1, N'
Món quà giỏ hoa hướng dương, đồng tiền và hoa hồng kem tượng trưng cho may mắn, tài lộc và sức sống bền bỉ. Thích hợp cho các dịp kỷ niệm, sinh nhật và buổi hợp tác kinh doanh.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (20, 2, N'Hoa Hồng Trắng Ngọc', N'HoaHongTrangNgoc.jpg', CAST(1000000.00 AS Decimal(10, 2)), 2, 1, N'Hoa hồng trắng trên bàn tượng trưng cho thanh khiết, lòng biết ơn và sự bình yên. Màu trắng đại diện cho hòa bình và tinh tế, tạo không gian trang trí ấm áp và ý nghĩa.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (21, 3, N'Sự Nghiệp Đỏ Rực', N'SuNghiepDoRuc.jpg', CAST(2000000.00 AS Decimal(10, 2)), 1, 1, N'Hoa hồng đỏ khi được tặng trong buổi khai trương biểu hiện sự may mắn, sự tôn trọng, tự tin và sức mạnh. Màu đỏ rực rỡ của hoa hồng đại diện cho sự đam mê và hy vọng vào sự phát triển và thành công trong tương lai.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (22, 3, N'Vinh Danh Vàng', N'VinhDanhVang.jpg', CAST(1500000.00 AS Decimal(10, 2)), 1, 1, N'Hoa khai trương bằng hoa hồng vàng tượng trưng cho sự thịnh vượng, hạnh phúc và thành công. Màu vàng biểu hiện cho sự may mắn và tươi mới trong sự khởi đầu mới.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (25, 3, N'Đẳng Cấp Vẻ Đẹp', N'DangCapVeDep.jpg', CAST(1500000.00 AS Decimal(10, 2)), 1, 1, N'
Hoa khai trương kết hợp Hoa Lan Hồ Điệp, Hoa Lan Vũ Nữ, Hoa Lan Mokara, Lá Môn Đỏ và Hoa Lá Phụ biểu hiện sự thanh lịch, độc đáo và may mắn trong bắt đầu mới.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (27, 3, N'Sự Thịnh Vượng Nở Hoa', N'SuThinhVuonNoHoa.jpg', CAST(2000000.00 AS Decimal(10, 2)), 1, 1, N'Hoa khai trương với hoa đồng tiền và hoa lan biểu hiện sự thịnh vượng, may mắn và hạnh phúc trong sự khởi đầu mới, mang đến cảm giác hạnh phúc và thành công.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (28, 3, N'Nở Hoa Thành Công', N'NoHoaThanhCong.jpg', CAST(2000000.00 AS Decimal(10, 2)), 1, 1, N'Kết hợp các loại hoa hồng trong hoa khai trương biểu hiện sự đa dạng và hạnh phúc, tượng trưng cho lòng chân thành và mong muốn về tương lai thịnh vượng và hạnh phúc.')
INSERT [dbo].[product] ([product_id], [category_id], [name], [image], [price], [quantity], [is_available], [description]) VALUES (30, 3, N'Tiền Tài Thịnh Vượng', N'TienTaiThinhVuong.jpg', CAST(2000000.00 AS Decimal(10, 2)), 1, 1, N'Hoa khai trương bằng hoa đồng tiền đại diện cho sự thịnh vượng, tài lộc và may mắn trong bắt đầu mới. Nó biểu hiện lòng chân thành và mong muốn cho một tương lai đầy hạnh phúc và thành công.




')
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([role_id], [role_name]) VALUES (1, N'Admin')
INSERT [dbo].[role] ([role_id], [role_name]) VALUES (2, N'Customer')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
ALTER TABLE [dbo].[_order]  WITH CHECK ADD FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([customer_id])
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([role_id])
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[account] ([email])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[_order] ([order_id])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([product_id])
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([category_id])
GO
USE [master]
GO
ALTER DATABASE [DATN_ShopHoaDaisy_V2] SET  READ_WRITE 
GO
