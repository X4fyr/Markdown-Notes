package de.x4fyr.markdownnotes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Fragment handling the edit of a single note, as a part of the EditorActivity.
 */
public class EditorFragment extends Fragment {

    private EditorActivity editorActivity;

    @Override
    public void onAttach(Context context) {
        this.editorActivity = (EditorActivity) context;

        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editor, container, false);
    }

    @Override
    public void onStart() {
        //noinspection ConstantConditions
        EditText editorEditText = (EditText) getView().findViewById(R.id.editor_textEdit);

        editorEditText.setText(editorActivity.getNote().getContent());

        EditorActivity context = (EditorActivity) getContext();
        editorEditText.addTextChangedListener(context.editorWatcher);
        editorEditText.setHorizontallyScrolling(true);

        super.onStart();
    }
}
