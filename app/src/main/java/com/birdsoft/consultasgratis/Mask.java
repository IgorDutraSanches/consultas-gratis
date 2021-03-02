package com.birdsoft.consultasgratis;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public abstract class Mask {
    public static String unmask(String str) {
        return str.replaceAll("[^\\dQWERTYUIOPÇLKJHGFDSAZXCVBNMqazxswedcvfrtgbnhyujmkiolpç]", "").replaceAll("[,]", ".");
    }

    public static String unmaskKeyboard(String str) {
        return str.replaceAll("[^\\dQWERTYUIOPÇLKJHGFDSAZXCVBNMqazxswedcvfrtgbnhyujmkiolpç]", "");
    }

    public static TextWatcher insert(final String str, final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                char[] charArray;
                String unmask = Mask.unmask(charSequence.toString());
                if (this.isUpdating) {
                    this.old = unmask;
                    this.isUpdating = false;
                    return;
                }
                String str = "";
                int i4 = 0;
                for (char c : str.toCharArray()) {
                    if (c == '#' || unmask.length() <= this.old.length()) {
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(unmask.charAt(i4));
                            str = sb.toString();
                            i4++;
                        } catch (Exception unused) {
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(c);
                        str = sb2.toString();
                    }
                }
                this.isUpdating = true;
                editText.setText(str);
                editText.setSelection(str.length());
            }
        };
    }

    public static TextWatcher insertCnpj(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String mask;
            String old;

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                String str = "";
                this.mask = str;
                this.old = str;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                char[] charArray;
                String unmask = Mask.unmask(charSequence.toString());
                this.mask = "##.###.###/####-##";

                if (this.isUpdating) {
                    this.old = unmask;
                    this.isUpdating = false;
                    return;
                }
                String str = "";
                int i4 = 0;
                for (char c : this.mask.toCharArray()) {
                    if (c == '#' || unmask.length() <= this.old.length()) {
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(unmask.charAt(i4));
                            str = sb.toString();
                            i4++;
                        } catch (Exception unused) {
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(c);
                        str = sb2.toString();
                    }
                }
                this.isUpdating = true;
                editText.setText(str);
                editText.setSelection(str.length());
            }
        };
    }

    public static TextWatcher insertIP(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String mask;
            String old;

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                String str = "";
                this.mask = str;
                this.old = str;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                char[] charArray;
                String unmask = Mask.unmask(charSequence.toString());
                this.mask = "###.###.###.###";

                if (this.isUpdating) {
                    this.old = unmask;
                    this.isUpdating = false;
                    return;
                }
                String str = "";
                int i4 = 0;
                for (char c : this.mask.toCharArray()) {
                    if (c == '#' || unmask.length() <= this.old.length()) {
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(unmask.charAt(i4));
                            str = sb.toString();
                            i4++;
                        } catch (Exception unused) {
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(c);
                        str = sb2.toString();
                    }
                }
                this.isUpdating = true;
                editText.setText(str);
                editText.setSelection(str.length());
            }
        };
    }

    public static TextWatcher insertPlaca(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String mask;
            String old;

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                String str = "";
                this.mask = str;
                this.old = str;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                char[] charArray;
                String unmask = Mask.unmask(charSequence.toString());
                this.mask = "###-####";

                if (this.isUpdating) {
                    this.old = unmask;
                    this.isUpdating = false;
                    return;
                }
                String str = "";
                int i4 = 0;
                for (char c : this.mask.toCharArray()) {
                    if (c == '#' || unmask.length() <= this.old.length()) {
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(unmask.charAt(i4));
                            str = sb.toString();
                            i4++;
                        } catch (Exception unused) {
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(c);
                        str = sb2.toString();
                    }
                }
                this.isUpdating = true;
                editText.setText(str.toUpperCase());
                editText.setSelection(str.length());
            }
        };
    }

    public static TextWatcher insertCep(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String mask;
            String old;

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                String str = "";
                this.mask = str;
                this.old = str;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                char[] charArray;
                String unmask = Mask.unmask(charSequence.toString());
                this.mask = "#####-###";

                if (this.isUpdating) {
                    this.old = unmask;
                    this.isUpdating = false;
                    return;
                }
                String str = "";
                int i4 = 0;
                for (char c : this.mask.toCharArray()) {
                    if (c == '#' || unmask.length() <= this.old.length()) {
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(unmask.charAt(i4));
                            str = sb.toString();
                            i4++;
                        } catch (Exception unused) {
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(c);
                        str = sb2.toString();
                    }
                }
                this.isUpdating = true;
                editText.setText(str.toUpperCase());
                editText.setSelection(str.length());
            }
        };
    }
}
