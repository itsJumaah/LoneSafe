namespace LoneSafe
{
    partial class LoadingScreen
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LoadingScreen));
            this.title = new System.Windows.Forms.Label();
            this.pow = new System.Windows.Forms.Label();
            this.baitLogo = new System.Windows.Forms.PictureBox();
            this.logo = new System.Windows.Forms.PictureBox();
            this.copyrights = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.baitLogo)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.logo)).BeginInit();
            this.SuspendLayout();
            // 
            // title
            // 
            this.title.AutoSize = true;
            this.title.Font = new System.Drawing.Font("Century Gothic", 36F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.title.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.title.Location = new System.Drawing.Point(54, 232);
            this.title.Name = "title";
            this.title.Size = new System.Drawing.Size(241, 58);
            this.title.TabIndex = 2;
            this.title.Text = "LoneSafe";
            // 
            // pow
            // 
            this.pow.AutoSize = true;
            this.pow.Font = new System.Drawing.Font("Courier New", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.pow.ForeColor = System.Drawing.Color.Teal;
            this.pow.Location = new System.Drawing.Point(108, 292);
            this.pow.Name = "pow";
            this.pow.Size = new System.Drawing.Size(133, 15);
            this.pow.TabIndex = 3;
            this.pow.Text = "Powered by B.A.I.T";
            // 
            // baitLogo
            // 
            this.baitLogo.Image = global::LoneSafe.Properties.Resources.baitlogo;
            this.baitLogo.Location = new System.Drawing.Point(150, 310);
            this.baitLogo.Name = "baitLogo";
            this.baitLogo.Size = new System.Drawing.Size(50, 50);
            this.baitLogo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.baitLogo.TabIndex = 4;
            this.baitLogo.TabStop = false;
            // 
            // logo
            // 
            this.logo.Image = global::LoneSafe.Properties.Resources.logo;
            this.logo.Location = new System.Drawing.Point(79, 37);
            this.logo.Name = "logo";
            this.logo.Size = new System.Drawing.Size(192, 192);
            this.logo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize;
            this.logo.TabIndex = 0;
            this.logo.TabStop = false;
            // 
            // copyrights
            // 
            this.copyrights.AutoSize = true;
            this.copyrights.ForeColor = System.Drawing.Color.MidnightBlue;
            this.copyrights.Location = new System.Drawing.Point(63, 478);
            this.copyrights.Name = "copyrights";
            this.copyrights.Size = new System.Drawing.Size(223, 13);
            this.copyrights.TabIndex = 5;
            this.copyrights.Text = "B.A.I.T © Copyrights 2016 - All rights reserved";
            this.copyrights.TextAlign = System.Drawing.ContentAlignment.BottomCenter;
            // 
            // LoadingScreen
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Lavender;
            this.ClientSize = new System.Drawing.Size(350, 500);
            this.Controls.Add(this.copyrights);
            this.Controls.Add(this.baitLogo);
            this.Controls.Add(this.pow);
            this.Controls.Add(this.title);
            this.Controls.Add(this.logo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "LoadingScreen";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "LoadingScreen";
            ((System.ComponentModel.ISupportInitialize)(this.baitLogo)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.logo)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox logo;
        private System.Windows.Forms.Label title;
        private System.Windows.Forms.Label pow;
        private System.Windows.Forms.PictureBox baitLogo;
        private System.Windows.Forms.Label copyrights;
    }
}