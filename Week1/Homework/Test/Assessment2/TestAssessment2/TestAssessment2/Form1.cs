using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TestAssessment2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            
        }

        public string f(string str)
        {
            string Result = string.Empty;
            List<char> AlphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".ToCharArray().ToList();
            List<char> AlphabetLower = "abcdefghijklmnopqrstuvwxyz".ToCharArray().ToList();

            List<char> StringDecomposed = str.ToCharArray().ToList();
            int OriginalIndex = 0;
            int NewIndex = 0;

            for (int i = 0; i < StringDecomposed.Count; i++)
            {
                if (!AlphabetUpper.Contains(StringDecomposed[i]) && !AlphabetLower.Contains(StringDecomposed[i]))
                {
                    Result += StringDecomposed[i];
                }
                else if(AlphabetUpper.Contains(StringDecomposed[i]))
                {
                    OriginalIndex = AlphabetUpper.IndexOf(StringDecomposed[i]);
                    NewIndex = AlphabetUpper.Count - OriginalIndex;
                    Result += AlphabetUpper[NewIndex - 1];
                }
                else
                {
                    OriginalIndex = AlphabetLower.IndexOf(StringDecomposed[i]);
                    NewIndex = AlphabetLower.Count - OriginalIndex;
                    Result += AlphabetLower[NewIndex - 1];
                }
            }

            return Result;
        }

        public string g(string str)
        {
            StringBuilder sbResult = new StringBuilder();
            sbResult.AppendLine("{");
            List<char> Text = str.ToCharArray().ToList();
            List<char> AlphabetLower = "abcdefghijklmnopqrstuvwxyz".ToCharArray().ToList();

            for (int i = 0; i < AlphabetLower.Count; i++)
            {
                int LetterFrecuency = Text.Count(a => a.ToString().ToLower() == AlphabetLower[i].ToString().ToLower());
                sbResult.AppendLine(string.Format("\t{0}: {1},", AlphabetLower[i], LetterFrecuency));
            }

            sbResult.AppendLine("}");
            return sbResult.ToString();
        }

        private void btnEncode_Click(object sender, EventArgs e)
        {
            string Input = txtInput.Text;
            txtResult.Text = f(Input);
        }

        private void btnCheckFrecuency_Click(object sender, EventArgs e)
        {
            string Input = txtInput.Text;
            txtResult.Text = g(Input);
        }
    }
}
