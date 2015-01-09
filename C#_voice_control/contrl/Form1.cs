using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.IO;
using System.Text;
using System.Windows.Forms;
using System.Speech.Synthesis;
using System.Speech.Recognition;
using System.Net.Sockets;
namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        static SpeechRecognitionEngine sprec = new SpeechRecognitionEngine();
        static SpeechSynthesizer spsys = new SpeechSynthesizer();
        static TcpClient clisend = new TcpClient(/*server's IP her*/, 9000);
        static NetworkStream net = clisend.GetStream();
        static StreamWriter sw = new StreamWriter(net);
     
        public Form1()
        {
            InitializeComponent();
        }

        private void richTextBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
              
        }

        private void Form1_Load(object sender, EventArgs e)
        {
           
            sw.WriteLine("localhost");

            
            sw.Flush();
           
            Choices c = new Choices(new string[] {"Intro", "start", "search", "Front", "back", "left", "right" });
            sprec.LoadGrammarAsync(new Grammar(new GrammarBuilder(c)));
           

        }
        private void SpeechRecognized(Object o, SpeechRecognizedEventArgs e)
        {
            switch (e.Result.Text)
            {
                case "Intro":
                    spsys.SpeakAsync(" I am aaagna created by Abhishek, Gautam, Navneet  and Adityaa");
                    richTextBox1.AppendText("\n"+e.Result.Text);
                    
                    break;
                case "start":
                    spsys.SpeakAsync("hello");
                    richTextBox1.AppendText("\n"+e.Result.Text);
                    sw.WriteLine("9000" + " " + "DATA" + " " + "start");
                    sw.Flush();
                   
                    break;
                case "front":
                    spsys.SpeakAsync("going front");
                    richTextBox1.AppendText("\n"+e.Result.Text);
                    sw.WriteLine("9000" + " " + "DATA" + " " + "front");
                    sw.Flush();
                    break;
                case "back":
                    spsys.SpeakAsync("going back");
                    richTextBox1.AppendText("\n"+e.Result.Text);
                    sw.WriteLine("9000" + " " + "DATA" + " " + "back");
                    sw.Flush();
                    break;
                case "left":
                    spsys.SpeakAsync("going left");
                    richTextBox1.AppendText("\n"+e.Result.Text);
                    sw.WriteLine("9000" + " " + "DATA" + " " + "left");
                    sw.Flush();
                    break;
                case "right":
                    spsys.SpeakAsync("going right");
                    richTextBox1.AppendText("\n"+e.Result.Text);
                    sw.WriteLine("9000" + " " + "DATA" + " " + "right");
                    sw.Flush();
                    break;
                case "cam":
                    spsys.SpeakAsync("starting camera");
                    richTextBox1.AppendText("\n"+e.Result.Text);
                    sw.WriteLine("9000" + " " + "DATA" + " " + "cam");
                    sw.Flush();
                    break;

            }
        }
        private void button2_Click(object sender, EventArgs e)
        {
            button2.Enabled = false;
            sprec.SetInputToDefaultAudioDevice();
            sprec.RecognizeAsync(RecognizeMode.Multiple);
            sprec.SpeechRecognized += SpeechRecognized;
            
        }
     

    }

        
}
