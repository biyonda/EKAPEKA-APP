package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.diskominfo.ekpkdisnaker.R;

public class BahasaActivity extends AppCompatActivity {

    ImageView btn_back;
    LinearLayout btn_simpan;
    Switch bhs_inggris,bhs_mandarin,bhs_jepang,bhs_korea,bhs_jerman,bhs_rusia,bhs_italia,bhs_hindi,bhs_lainnya;
    String list_bahasa_pencaker;
    Integer sts_inggris = 0, sts_mandarin = 0, sts_jepang = 0, sts_korea = 0, sts_jerman = 0, sts_rusia = 0, sts_italia = 0, sts_hindi = 0, sts_lainnya = 0;
    String txt_inggris = "", txt_mandarin = "", txt_jepang = "", txt_korea = "", txt_jerman = "", txt_rusia = "", txt_italia = "", txt_hindi = "", txt_lainnya = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahasa);

        btn_back = findViewById(R.id.btn_back);
        bhs_inggris = findViewById(R.id.bhs_inggris);
        bhs_mandarin = findViewById(R.id.bhs_mandarin);
        bhs_jepang = findViewById(R.id.bhs_jepang);
        bhs_korea = findViewById(R.id.bhs_korea);
        bhs_jerman = findViewById(R.id.bhs_jerman);
        bhs_rusia = findViewById(R.id.bhs_rusia);
        bhs_italia = findViewById(R.id.bhs_italia);
        bhs_hindi = findViewById(R.id.bhs_hindi);
        bhs_lainnya = findViewById(R.id.bhs_lainnya);
        btn_simpan = findViewById(R.id.btn_simpan);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bhs_inggris.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_inggris = 1;
                } else {
                    sts_inggris = 0;
                }
            }
        });

        bhs_mandarin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_mandarin = 1;
                } else {
                    sts_mandarin = 0;
                }
            }
        });

        bhs_jepang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_jepang = 1;
                } else {
                    sts_jepang = 0;
                }
            }
        });

        bhs_korea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_korea = 1;
                } else {
                    sts_korea = 0;
                }
            }
        });

        bhs_jerman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_jerman = 1;
                } else {
                    sts_jerman = 0;
                }
            }
        });

        bhs_rusia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_rusia = 1;
                } else {
                    sts_rusia = 0;
                }
            }
        });

        bhs_italia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_italia = 1;
                } else {
                    sts_italia = 0;
                }
            }
        });

        bhs_hindi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_hindi = 1;
                } else {
                    sts_hindi = 0;
                }
            }
        });

        bhs_lainnya.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sts_lainnya = 1;
                } else {
                    sts_lainnya = 0;
                }
            }
        });

        if (sts_inggris == 0) {
            txt_inggris = "Bahasa Inggris, ";
        } else {
            txt_inggris = "";
        }

        if (sts_mandarin == 0) {
            txt_mandarin = "Bahasa Mandarin, ";
        } else {
            txt_mandarin = "";
        }

        if (sts_jepang == 0) {
            txt_jepang = "Bahasa Jepang, ";
        } else {
            txt_jepang = "";
        }

        if (sts_korea == 0) {
            txt_korea = "Bahasa Korea, ";
        } else {
            txt_korea = "";
        }

        if (sts_jerman == 0) {
            txt_jerman = "Bahasa Jerman, ";
        } else {
            txt_jerman = "";
        }

        if (sts_rusia == 0) {
            txt_rusia = "Bahasa Rusia, ";
        } else {
            txt_rusia = "";
        }

        if (sts_italia == 0) {
            txt_italia = "Bahasa Italia, ";
        } else {
            txt_italia = "";
        }

        if (sts_hindi == 0) {
            txt_hindi = "Bahasa Hindi, ";
        } else {
            txt_hindi = "";
        }

        if (sts_lainnya == 0) {
            txt_lainnya = "Lainnya, ";
        } else {
            txt_lainnya = "";
        }

        list_bahasa_pencaker = "Bahasa Indonesia, " + txt_inggris + txt_mandarin + txt_jepang + txt_korea + txt_jerman + txt_rusia + txt_italia + txt_hindi + txt_lainnya;

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(list_bahasa_pencaker);
            }
        });

    }
}