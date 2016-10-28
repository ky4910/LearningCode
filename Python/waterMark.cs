using System;

public class EventTest
{
	public class MyEventCls
	{
		private void InsertImage(string xh)
		{
			string cmdText = "select name,origitalbmp from studentinfotb where xh='" + xh + "'";
			SqlConnection conn = new SqlConnection(sqlConnectionString);
			conn.Open();
			SqlCommand cmd = new SqlCommand(cmdText, conn);
			SqlDataReader dr = cmd.ExecuteReader();
			if (dr.Read())
			{
				string name = dr[0].ToString();
				byte[] photo = (byte[])dr[1];
				dr.Close();
				System.IO.MemoryStream ms = new System.IO.MemoryStream(photo);
				System.Drawing.Image image = System.Drawing.Image.FromStream(ms);
				System.Drawing.Bitmap bt = new System.Drawing.Bitmap(image, 390, 566);
				string imageName = Server.MapPath(@"~/temppics/" + xh + "origitalbmp.jpg");
				bt.Save(imageName);
				bt.Dispose();
				DrawImage(imageName, "10280" + xh, name, 155.0f, 340.0f);
				UploadImage(xh, imageName);
			}
			conn.Close();
		}

		private void DrawImage(string filePath, string xueHao, string name, float xPosition, float yPosition)
		{
			System.Drawing.Image sourceImage = System.Drawing.Image.FromFile(filePath);
			sourceImage.RotateFlip(RotateFlipType.Rotate90FlipXY);
			Bitmap newImage = new Bitmap(sourceImage, sourceImage.Width, sourceImage.Height);
			//newImage.SetResolution(sourceImage.HorizontalResolution, sourceImage.VerticalResolution);
			newImage.SetResolution(100, 100);//设置分辨率的大小
			Graphics gh = Graphics.FromImage(newImage);
			gh.SmoothingMode = SmoothingMode.HighQuality;
			gh.DrawImage(newImage, new Rectangle(0, 0, sourceImage.Width, sourceImage.Height), 0, 0, sourceImage.Width, sourceImage.Height, GraphicsUnit.Pixel);
			Font newFont = null;
			SizeF fontSize = new SizeF();
			newFont = new Font("黑体", 22, FontStyle.Bold);
			fontSize = gh.MeasureString(xueHao, newFont);
			StringFormat format = new StringFormat();
			format.Alignment = StringAlignment.Center;
			SolidBrush brush = new SolidBrush(Color.White);
			gh.DrawString(xueHao, newFont, brush, new PointF(xPosition, yPosition), format);
			newImage.RotateFlip(RotateFlipType.Rotate270FlipXY);
			gh.DrawString(AddSplit(name), newFont, brush, new PointF(sourceImage.Height - yPosition - 13, xPosition + 109), format);
			newImage.Save(filePath, ImageFormat.Jpeg);
			gh.Dispose();
			sourceImage.Dispose();
		}

		private void UploadImage(string xh, string imageName)
		{
			FileStream fs = new FileStream(imageName, FileMode.Open, FileAccess.Read);
			BinaryReader br = new BinaryReader(fs);
			byte[] photo = br.ReadBytes((int)fs.Length);
			br.Close();
			fs.Close();
			SqlConnection conn = new SqlConnection(sqlConnectionString);
			conn.Open();
			//存储已经打了字的照片
			string cmdText = "update studentinfotb set bmp=@bmp where xh=@xh";
			SqlCommand cmd = new SqlCommand(cmdText, conn);
			cmd.Parameters.Add("@bmp", SqlDbType.Image);
			cmd.Parameters.Add("@xh", SqlDbType.VarChar);
			cmd.Parameters[0].Value = photo;
			cmd.Parameters[1].Value = xh;
			cmd.ExecuteNonQuery();
			conn.Close();
		}
		
		public static void Main()
		{
			Console.WriteLine("Error!");
		}
	}
}

