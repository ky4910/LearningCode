using System;
using System.Collections.Generic;
using System.Web;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Drawing.Imaging;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using System.Diagnostics;

namespace WaterPhoto
{
    public class Program
    {
        private void InsertImage(string xh)
        {
            string cmdText = "select FullName, PhotoUri from dbo.YingXin_XinShengZhaoPian where Persno='" + xh + "'";
            string sqlConnectionString =
                "Database=MDBS; data source = db3.shu.edu.cn; User Id = AlumniDB; Password = WLH_gW96_p; MultipleActiveResultSets = true;";
            SqlConnection conn = new SqlConnection(sqlConnectionString);
            conn.Open();
            SqlCommand cmd = new SqlCommand(cmdText, conn);
            SqlDataReader dr = cmd.ExecuteReader();
            if (dr.Read())
            {
                string name = dr[0].ToString();

                //download image by url
                WebRequest webRequest = WebRequest.Create(dr[1].ToString());
                HttpWebRequest request = webRequest as HttpWebRequest;
                WebResponse response = request.GetResponse();
                Stream stream = response.GetResponseStream();
                Image image = Image.FromStream(stream);

                dr.Close();
                System.Drawing.Bitmap bt = new System.Drawing.Bitmap(image, 390, 566);
                string imageName = "F:\\Code\\CSharp\\newPhoto\\cur\\" + xh + "origitalbmp.jpg";
                bt.Save(imageName);
                bt.Dispose();

                if (name.Length == 4)
                {
                    DrawImage(imageName, "10280" + xh, name, 125.0f, 340.0f);
                }
                else
                {
                    DrawImage(imageName, "10280" + xh, name, 155.0f, 340.0f);
                }
                
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

            if (name.Length < 5)
            {
                gh.DrawString(AddSplit(name), newFont, brush, new PointF(sourceImage.Height - yPosition - 13, xPosition + 129), format);
            }

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
            string sqlConnectionString =
                "Database=MDBS; data source = db3.shu.edu.cn; User Id = AlumniDB; Password = WLH_gW96_p; MultipleActiveResultSets = true;";
            SqlConnection conn = new SqlConnection(sqlConnectionString);
            conn.Open();

            //存储已经打了字的照片
            string cmdText = "update dbo.YingXin_XinShengZhaoPian set bmp=@bmp where Persno=@xh";
            SqlCommand cmd = new SqlCommand(cmdText, conn);
            cmd.Parameters.Add("@bmp", SqlDbType.Image);
            cmd.Parameters.Add("@xh", SqlDbType.VarChar);
            cmd.Parameters[0].Value = photo;
            cmd.Parameters[1].Value = xh;
            cmd.ExecuteNonQuery();
            conn.Close();
        }

        private void DownImage(string xh)
        {
            string cmdText = "select FullName, Bmp from dbo.YingXin_XinShengZhaoPian where Persno='" + xh + "'";
            string sqlConnectionString =
                "Database=MDBS; data source = db3.shu.edu.cn; User Id = AlumniDB; Password = WLH_gW96_p; MultipleActiveResultSets = true;";
            SqlConnection conn = new SqlConnection(sqlConnectionString);
            conn.Open();
            SqlCommand cmd = new SqlCommand(cmdText, conn);
            SqlDataReader rd = cmd.ExecuteReader();
            rd.Read();
            MemoryStream buf = new MemoryStream((byte[])rd[1]);
            Image image = Image.FromStream(buf, true);
            
            System.Drawing.Bitmap bt = new System.Drawing.Bitmap(image, 390, 566);
            string imageName = "C:\\Users\\KIM\\Desktop\\Photo\\" + rd[0].ToString() + ".jpg";

            rd.Close();
            bt.Save(imageName);
            bt.Dispose();

            return;
        }

        private string AddSplit(string str)
        {
            string res = null;
            
            for (int i = 0; i < str.Length; i++)
            {
                res = res + str[i];
                res += "\n";
            }

            return res;
        }

        static void Main(string[] args)
        {
            string persNo = null;
            List<string> noLists = new List<string>();
            Program waterMap = new Program();
            var watch = Stopwatch.StartNew();

            StreamReader rFile = File.OpenText("C:\\Users\\KIM\\Desktop\\temp");
            while ((persNo = rFile.ReadLine()) != null)
            {
                noLists.Add(persNo);
                Console.Clear();
                Console.WriteLine(watch.Elapsed);
            }

            foreach (var xh in noLists)
            {
                waterMap.InsertImage(xh);
                Console.Clear();
                Console.WriteLine(watch.Elapsed);
            }

            watch.Stop();

            Console.WriteLine("Done!");
            Console.WriteLine("The list count is " + noLists.Count);
            Console.ReadLine();
        }
    }
}

